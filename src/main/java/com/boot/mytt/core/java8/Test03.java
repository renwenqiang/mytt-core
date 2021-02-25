package com.boot.mytt.core.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author renwq
 * @date 2021/2/25 9:40
 */
public class Test03 {
    public static void main(String[] args) {
        List<User> list = new ArrayList<>();
        list.add(new User(5, "张三"));
        list.add(new User(3, "李四"));
        list.add(new User(7, "王五"));
        list.add(new User(1, "赵六"));
        System.out.println(list);
        list.sort(Comparator.comparing(User::getId).reversed());
        System.out.println(list);
    }
}

class User{
    Integer id;
    String name;

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
