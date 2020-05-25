package com.boot.mytt.core.resttemplate;

import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Configuration
public class RestClientConfig {

    @Resource
    private CloseableHttpClient closeableHttpClient;

    @Resource
    private StringHttpMessageConverter stringHttpMessageConverter;

    @Resource
    private MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(httpComponentsClientHttpRequestFactory());
        restTemplate.setErrorHandler(new CustomErrorHandler());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(stringHttpMessageConverter);
        messageConverters.add(mappingJackson2HttpMessageConverter);
        restTemplate.setMessageConverters(messageConverters);
        return restTemplate;
    }

    @Bean
    public HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpComponentsClientHttpRequestFactory.setHttpClient(closeableHttpClient);
        httpComponentsClientHttpRequestFactory.setReadTimeout(5000);//单位为ms
        httpComponentsClientHttpRequestFactory.setConnectTimeout(5000);//单位为ms
        return httpComponentsClientHttpRequestFactory;
    }
}
