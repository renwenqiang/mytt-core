package com.boot.mytt.core.java8;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;

/**
 * 测试属性配置文件 - 配置参数的获取
 *
 * @author renwq
 * @date 2021/2/25 15:23
 */
public class Test02 {

    public static void main(String[] args) {

        String value = null;
        String key = "com.bean.renwq";
        try {
            value = PropertiesLoaderUtils.loadAllProperties("application.yml").getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(value);
    }
}
