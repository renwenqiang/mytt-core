package com.boot.mytt.core.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }
}
