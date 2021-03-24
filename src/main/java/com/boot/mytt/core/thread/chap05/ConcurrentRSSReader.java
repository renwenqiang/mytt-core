package com.boot.mytt.core.thread.chap05;

import com.boot.mytt.core.util.Tools;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author renwq
 * @date 2021/3/7 上午11:06
 */
public class ConcurrentRSSReader {

    public static void main(String[] args) throws Exception {
        String url = "http://lorem-rss.herokuapp.com/feed";
        InputStream in = issueRequest(url);
        Document document = parseXML(in);
        // 读取XML中的数据
        Element eleRss = (Element) document.getFirstChild();
        Element eleChannel = (Element) eleRss.getElementsByTagName("channel").item(
                0);
        Node ndTtile = eleChannel.getElementsByTagName("title").item(0);
        String title = ndTtile.getFirstChild().getNodeValue();
        System.out.println(title);
    }

    private static Document parseXML(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document document = db.parse(inputStream);
        return document;
    }

    private static InputStream loadRSS(final String url) throws IOException {
        final PipedInputStream in = new PipedInputStream();
        final PipedOutputStream pos = new PipedOutputStream(in);
        Thread workThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    doDownload(url, pos);
                } catch (Exception e) {
                    Tools.silentClose(pos, in);
                    e.printStackTrace();
                }
            }
        }, "rss-loader");
        workThread.start();
        return in;
    }

    static void doDownload(String url, OutputStream out) throws Exception {
        ReadableByteChannel readChannel = null;
        WritableByteChannel writeChannel = null;
        try {
            BufferedInputStream in = issueRequest(url);
            readChannel = Channels.newChannel(in);
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            writeChannel = Channels.newChannel(out);
            while (readChannel.read(buffer) > 0) {
                buffer.flip();
                writeChannel.write(buffer);
                buffer.clear();
            }
        } finally {
            Tools.silentClose(readChannel, writeChannel);
        }
    }

    static BufferedInputStream issueRequest(String url) throws Exception {
        URL requestURL = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
        conn.setConnectTimeout(2000);
        conn.setReadTimeout(2000);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Connection", "close");
        conn.setDoInput(true);
        conn.connect();
        int statusCode = conn.getResponseCode();
        if (HttpURLConnection.HTTP_OK != statusCode) {
            conn.disconnect();
            throw new Exception("Server exception, status code: " + statusCode);
        }
        BufferedInputStream in = new BufferedInputStream(conn.getInputStream()) {
            @Override
            public void close() throws IOException {
                try {
                    super.close();
                } finally {
                    conn.disconnect();
                }
            }
        };
        return in;
    }
}
