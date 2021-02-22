package com.boot.mytt.core.thread.chap01;

import com.boot.mytt.core.thread.util.Debug;
import com.boot.mytt.core.thread.util.Tools;
import io.netty.buffer.ByteBuf;
import lombok.SneakyThrows;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * @author renwq
 * @date 2021/2/21 上午9:46
 */
public class FileDownloaderApp {
    public static void main(String[] args) {
        String fileUrl = "https://fcww33.com/get_file/1/f38ff28ff0d2aa2814d8f68ad2128825601b774415/20000/20101/20101.mp4";
        Thread downloadThread = new Thread(new FileDownloader(fileUrl));
        downloadThread.start();
    }

    static class FileDownloader implements Runnable{

        private final String fileURL;

        public FileDownloader(String fileURL) {
            this.fileURL = fileURL;
        }

        @Override
        public void run() {
            Debug.info("Download file from: " + fileURL);
            String fileBaseName = fileURL.substring(fileURL.lastIndexOf("/") + 1);
            try {
                URL url = new URL(fileURL);
                String localFileName = System.getProperty("java.io.tmpdir") +
                        "/viscent-" +
                        fileBaseName;
                Debug.info("Saving to: " + localFileName);
                downloadFile(url, new FileOutputStream(localFileName), 1024);
            } catch (MalformedURLException | FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void downloadFile(URL url, OutputStream outputStream, int bufSize) throws IOException {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            ReadableByteChannel inChannel = null;
            WritableByteChannel outChannel = null;
            try {
                int responseCode = httpURLConnection.getResponseCode();
                if (2 !=responseCode / 100) {
                    throw new IOException("Error. HTTP CODE: " + responseCode);
                }
                if (0 == httpURLConnection.getContentLength()) {
                    Debug.info("Noting to be download. URL: " + fileURL);
                    return;
                }
                inChannel = Channels.newChannel(new BufferedInputStream(httpURLConnection.getInputStream()));
                outChannel = Channels.newChannel((new BufferedOutputStream(outputStream)));
                ByteBuffer byteBuffer = ByteBuffer.allocate(bufSize);
                while (-1 != inChannel.read(byteBuffer)) {
                    byteBuffer.flip();
                    outChannel.write(byteBuffer);
                    byteBuffer.clear();
                }
            } finally {
                Tools.silentClose(inChannel, outChannel);
                httpURLConnection.disconnect();
            }
        }
    }
}
