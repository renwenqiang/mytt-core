package com.boot.mytt.core.java8;

import java.util.function.Consumer;

public class TestMain {

    public static void main(String[] args) {
        Person person  = new Person();
        person.setId(1L);
        person.setName("张三");
        person.setAge(22);
        Consumer<String> walkConsumer = person::walk;
        walkConsumer.accept("黄山路");
    }
}
