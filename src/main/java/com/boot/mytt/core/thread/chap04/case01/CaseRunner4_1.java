package com.boot.mytt.core.thread.chap04.case01;

import com.boot.mytt.core.thread.util.Debug;

/**
 * @author renwq
 * @date 2021/2/28 下午7:03
 */
public class CaseRunner4_1 {

    public static void main(String[] args) throws Exception {

        String url = "https://downloads.apache.org/tomcat/tomcat-10/v10.0.2/bin/apache-tomcat-10.0.2.zip";
        BigFileDownloader downloader = new BigFileDownloader(url);

        // 下载线程数
        int workerThreadsCount = 4;
        long reportInterval = 3L;

        Debug.info("downloading %s%nConfig:worker threads:%s,reportInterval:%s s.",
                url, workerThreadsCount, reportInterval);

        downloader.download(workerThreadsCount, reportInterval * 1000);
    }
}
