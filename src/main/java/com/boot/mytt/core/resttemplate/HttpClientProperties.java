package com.boot.mytt.core.resttemplate;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "renwq.httpclient")
public class HttpClientProperties {
    // 链接超时时间
    private int connecttimeout = 30000;

    // 请求超时时间
    private int requesttimeout = 30000;

    // socket 超时时间
    private int sockettimeout = 60000;

    // 最大连接数
    private int maxtotalconnections = 50;

    // 同路路由并发数
    private int defaultmaxperroute = 10;

    // 连接保持活跃的时间（Keep-Alive）
    private int defaultkeepalivetimemillis = 20 * 1000;

    // 空闲连接的生存时间
    private int closeidleconnectionwaittimesecs = 30;

    //重试次数
    private int defaulthttprequestretry = 3;

    //是否开启重试
    private boolean requestsentretryenabled = true;
}
