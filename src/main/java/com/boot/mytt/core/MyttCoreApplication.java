package com.boot.mytt.core;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author renwq
 * @date 2020/5/25
 */
@MapperScan(basePackages = {"com.boot.mytt.core.mapper"})
@SpringBootApplication
public class MyttCoreApplication {

    public static void main(String[] args) {
        /**
         * 第1种启动方式
         */
        SpringApplication.run(MyttCoreApplication.class, args);
        /**
         * 第2种启动方式
         */
//        SpringApplication app = new SpringApplication(MyttCoreApplication.class);
//        app.setBannerMode(Banner.Mode.OFF);
//        app.run(args);
        /**
         * 第3种启动方式
         */
//        new SpringApplicationBuilder().bannerMode(Banner.Mode.OFF)
//                .sources(MyttCoreApplication.class)
//                .build(args)
//                .run();
    }

}
