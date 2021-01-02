package com.boot.mytt.core.java8;

public class Person {
    private Long id;
    private String name;
    private Integer age;

    public void walk(String roadName) {
        System.out.println(name + " 在" + roadName + "上走");
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
