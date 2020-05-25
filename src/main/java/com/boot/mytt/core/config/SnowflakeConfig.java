package com.boot.mytt.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author renwq
 * @date 2020/5/25
 */
@Configuration
public class SnowflakeConfig {

    @Bean
    public Snowflake snowflake() {
        return new Snowflake();
    }
}
