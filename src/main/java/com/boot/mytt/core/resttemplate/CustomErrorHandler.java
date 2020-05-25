package com.boot.mytt.core.resttemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.DefaultResponseErrorHandler;

import java.io.IOException;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Slf4j
public class CustomErrorHandler extends DefaultResponseErrorHandler {
    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        log.error("请求失败 statusCode{} statusText{}", response.getStatusCode(), response.getStatusText());
    }
}
