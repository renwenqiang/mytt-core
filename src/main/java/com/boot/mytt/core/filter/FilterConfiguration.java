package com.boot.mytt.core.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }
}
