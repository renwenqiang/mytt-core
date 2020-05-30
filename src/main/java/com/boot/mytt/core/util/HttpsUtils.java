package com.boot.mytt.core.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
public class HttpsUtils {
   
   /**
    * 常用的GET方式
    * get （@RequestParam("vin")String vin可以接收）
    * 
    * @param host
    * @param path
    * @param headers
    * @param querys
    * @return
    * @throws Exception
    */
   public static HttpResponse doGet(String host, String path,
                                     Map<String, String> headers,
                                     Map<String, String> querys)
            throws Exception {     
       HttpClient httpClient = wrapClient(host);
 
       HttpGet request = new HttpGet(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
           request.addHeader(e.getKey(), e.getValue());
        }
        
        return httpClient.execute(request);
    }
 
   /**
    * 调用例子 doGet
    */
// @GetMapping("/doGetTest")
// public  String doGetTest() throws Exception {
//    String IP = "http://localhost:8075";
//    String path="/testTest";
//    Map<String, String> headers = new HashMap<String, String>();
//    //headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//    Map<String, String> querys = new HashMap<String, String>();
//    querys.put("vin","VIN223333");
//    querys.put("sn","SN666666666");
//    HttpResponse response = HttpsUtils.doGet(IP, path, headers, querys);
//    得到返回的参数
//    String  getResponse = EntityUtils.toString(response.getEntity());
//
//    return getResponse;
// }
 
 
   /**
    *  常用的Post方式
    *  Post String (传递json格式的String，@RequestBody String testData 可以接收)
    * 对于得到参数格式为：{"vin":"VIN223333","sn":"SN666666666"}
    * @param host
    * @param path
    * @param method
    * @param headers
    * @param querys
    * @param body
    * @return
    * @throws Exception
    */
   public static HttpResponse doPost(String host, String path, String method,
                             Map<String, String> headers,
                             Map<String, String> querys,
                             String body)
         throws Exception {
      HttpClient httpClient = wrapClient(host);
 
      HttpPost request = new HttpPost(buildUrl(host, path, querys));
      for (Map.Entry<String, String> e : headers.entrySet()) {
         request.addHeader(e.getKey(), e.getValue());
      }
 
      if (StringUtils.isNotBlank(body)) {
         request.setEntity(new StringEntity(body, "utf-8"));
      }
 
      return httpClient.execute(request);
   }
 
   /**
    * 调用例子 doPost
    */
// @PostMapping("/doPostTest")
// public  String doPostTest() throws Exception {
//    String IP = "http://localhost:8075";
//    String path="/testPostTest";
//    Map<String, String> headers = new HashMap<String, String>();
//    headers.put("Content-Type", "application/json; charset=UTF-8");
//    Map<String, String> querys = new HashMap<String, String>();
//
//    Map<String, String> bodys = new HashMap<String, String>();
//    bodys.put("vin","VIN223333");
//    bodys.put("sn","SN666666666");
//    String jsonStr= JSONObject.toJSONString(bodys);
//
//    HttpResponse response = HttpsUtils.doPost(IP, path,"POST", headers, querys,jsonStr);
//    得到返回的参数
//      String  getResponse = EntityUtils.toString(response.getEntity());
//
//    return getResponse;
// }
 
 
 
   /**
    * basic auth认证 Get方式
    */
   public static String doGetWithBasicAuth(String url, String basic_userName, String basic_password) throws IOException {
 
      // 创建HttpClientBuilder
      HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
      // 设置BasicAuth
      CredentialsProvider provider = new BasicCredentialsProvider();
      // Create the authentication scope
      AuthScope scope = new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT, AuthScope.ANY_REALM);
      // Create credential pair，在此处填写用户名和密码
      UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(basic_userName, basic_password);
      // Inject the credentials
      provider.setCredentials(scope, credentials);
      // Set the default credentials provider
      httpClientBuilder.setDefaultCredentialsProvider(provider);
      // HttpClient
      CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
 
      String result = "";
      HttpGet httpGet = null;
      HttpResponse httpResponse = null;
      HttpEntity entity = null;
 
      httpGet = new HttpGet(url);
      try {
         httpResponse = closeableHttpClient.execute(httpGet);
         entity = httpResponse.getEntity();
         if (entity != null) {
            result = EntityUtils.toString(entity);
         }
      } catch (ClientProtocolException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
 
      // 关闭连接
      closeableHttpClient.close();
 
 
      return result;
   }
 
   /**
    * 调用举例
    */
// String url = "http://140.xxx.22.xxx:18089/apxxx/" + vin;
// //basic auth中的用户名和密码
// String basic_userName = "admin";
// String basic_password = "public";
// 得到返回的参数
// String resultStr = HttpClientUtilCIV.doGetWithBasicAuth(url, basic_userName, basic_password);
 
 
   /**
    * post form（传递的是用&符号连接的字符串，@RequestBody String testData可以接收）
    *  对于得到参数格式为：vin=111&sn=12335
    * @param host
    * @param path
    * @param headers
    * @param querys
    * @param bodys
    * @return
    * @throws Exception
    */
   public static HttpResponse doPost(String host, String path,
                             Map<String, String> headers,
                             Map<String, String> querys,
                             Map<String, String> bodys)
         throws Exception {
      HttpClient httpClient = wrapClient(host);
 
      HttpPost request = new HttpPost(buildUrl(host, path, querys));
      for (Map.Entry<String, String> e : headers.entrySet()) {
         request.addHeader(e.getKey(), e.getValue());
      }
 
      if (bodys != null) {
         List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
 
         for (String key : bodys.keySet()) {
            nameValuePairList.add(new BasicNameValuePair(key, bodys.get(key)));
         }
         UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nameValuePairList, "utf-8");
         formEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
         request.setEntity(formEntity);
      }
 
      return httpClient.execute(request);
   }
 
 
 
 
 
   /**
    * Post stream
    * 
    * @param host
    * @param path
    * @param method
    * @param headers
    * @param querys
    * @param body
    * @return
    * @throws Exception
    */
   public static HttpResponse doPost(String host, String path, String method,
                                      Map<String, String> headers,
                                      Map<String, String> querys,
                                      byte[] body)
            throws Exception {     
       HttpClient httpClient = wrapClient(host);
 
       HttpPost request = new HttpPost(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
           request.addHeader(e.getKey(), e.getValue());
        }
 
        if (body != null) {
           request.setEntity(new ByteArrayEntity(body));
        }
 
        return httpClient.execute(request);
    }
   
   /**
    * Put String
    * @param host
    * @param path
    * @param method
    * @param headers
    * @param querys
    * @param body
    * @return
    * @throws Exception
    */
   public static HttpResponse doPut(String host, String path, String method,
                                     Map<String, String> headers,
                                     Map<String, String> querys,
                                     String body)
            throws Exception {     
       HttpClient httpClient = wrapClient(host);
 
       HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
           request.addHeader(e.getKey(), e.getValue());
        }
 
        if (StringUtils.isNotBlank(body)) {
           request.setEntity(new StringEntity(body, "utf-8"));
        }
 
        return httpClient.execute(request);
    }
   
   /**
    * Put stream
    * @param host
    * @param path
    * @param method
    * @param headers
    * @param querys
    * @param body
    * @return
    * @throws Exception
    */
   public static HttpResponse doPut(String host, String path, String method,
                                     Map<String, String> headers,
                                     Map<String, String> querys,
                                     byte[] body)
            throws Exception {     
       HttpClient httpClient = wrapClient(host);
 
       HttpPut request = new HttpPut(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
           request.addHeader(e.getKey(), e.getValue());
        }
 
        if (body != null) {
           request.setEntity(new ByteArrayEntity(body));
        }
 
        return httpClient.execute(request);
    }
   
   /**
    * Delete
    *  
    * @param host
    * @param path
    * @param method
    * @param headers
    * @param querys
    * @return
    * @throws Exception
    */
   public static HttpResponse doDelete(String host, String path, String method,
                                        Map<String, String> headers,
                                        Map<String, String> querys)
            throws Exception {     
       HttpClient httpClient = wrapClient(host);
 
       HttpDelete request = new HttpDelete(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
           request.addHeader(e.getKey(), e.getValue());
        }
        
        return httpClient.execute(request);
    }
 
 
 
   @SuppressWarnings("resource")
   public static String doPost(String host, String path,String jsonstr,String charset){
        String url=host+path;
      HttpClient httpClient = null;
      HttpPost httpPost = null;
      String result = null;
      try{
       httpClient = wrapClient(host);
 
//------------------------------4.1,4.2 版本设置时间-----------------------------
 
         //设置连接时间
         httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
         //设置数据传输时间
         httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 30000);
 
         httpPost = new HttpPost(url);
         httpPost.addHeader("Content-Type", "application/json");
 
         //此处是将请求体封装成为了StringEntity,若乱码则指定utf-8
         //StringEntity se = new StringEntity(jsonstr,"utf-8");
         StringEntity se = new StringEntity(jsonstr);
         se.setContentType("text/json");
         se.setContentEncoding(new BasicHeader("Content-Type", "application/json"));
         httpPost.setEntity(se);
         HttpResponse response = httpClient.execute(httpPost);
 
         if(response != null){
            HttpEntity resEntity = response.getEntity();
            if(resEntity != null){
               result = EntityUtils.toString(resEntity,charset);
            }
         }
      }catch(Exception ex){
//            ex.printStackTrace();
         //连接超时或者数据传输时间超时
         String apiResult="timeOut";
         return  apiResult;
      }
      return result;
   }
 
   /**
    *doPost 举例
    */
 
//    String IP = "http://localhost:8075";
//    String path="/testPostTest";
//    Map<String, String> bodys = new HashMap<String, String>();
//    bodys.put("vin","VIN223333");
//    bodys.put("sn","SN666666666");
//    String jsonStr= JSONObject.toJSONString(bodys);
//    String resultStr= HttpsUtils.doPost(IP,path,jsonStr, "utf-8");
//    return resultStr;
// }
 
 
   /**
    * 拼接url
    * @param host
    * @param path
    * @param querys
    * @return
    * @throws UnsupportedEncodingException
    */
   private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
       StringBuilder sbUrl = new StringBuilder();
       sbUrl.append(host);
       if (!StringUtils.isBlank(path)) {
          sbUrl.append(path);
        }
       if (null != querys) {
          StringBuilder sbQuery = new StringBuilder();
           for (Map.Entry<String, String> query : querys.entrySet()) {
              if (0 < sbQuery.length()) {
                 sbQuery.append("&");
              }
              if (StringUtils.isBlank(query.getKey()) && !StringUtils.isBlank(query.getValue())) {
                 sbQuery.append(query.getValue());
                }
              if (!StringUtils.isBlank(query.getKey())) {
                 sbQuery.append(query.getKey());
                 if (!StringUtils.isBlank(query.getValue())) {
                    sbQuery.append("=");
                    sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
                 }                
                }
           }
           if (0 < sbQuery.length()) {
              sbUrl.append("?").append(sbQuery);
           }
        }
       
       return sbUrl.toString();
    }
   
   private static HttpClient wrapClient(String host) {
      HttpClient httpClient = new DefaultHttpClient();
      if (host.startsWith("https://")) {
         sslClient(httpClient);
      }
      
      return httpClient;
   }
 
   private static void sslClient(HttpClient httpClient) {
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm = new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                @Override
                public void checkClientTrusted(X509Certificate[] xcs, String str) {
                   
                }
 
                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
 
                }
 
            };
            ctx.init(null, new TrustManager[] { tm }, null);
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = httpClient.getConnectionManager();
            SchemeRegistry registry = ccm.getSchemeRegistry();
            registry.register(new Scheme("https", 443, ssf));
        } catch (KeyManagementException ex) {
            throw new RuntimeException(ex);
        } catch (NoSuchAlgorithmException ex) {
           throw new RuntimeException(ex);
        }
    }
}
 
 
 