package com.boot.mytt.core.java8;

import java.util.*;
import java.util.stream.Collectors;

public class TestMain {

    public static void main(String[] args) {

        /*Test04 t4 = new Test04() {
            @Override
            public void fn() {
                System.out.println("fn is executing...");
            }
        };
        t4.fn();*/

        Test04 t5 = t -> t + "5";
        Object res = t5.fn(3);
        System.out.println(res);
        /*Person person  = new Person();
        person.setId(1L);
        person.setName("张三");
        person.setAge(22);
        Consumer<String> walkConsumer = person::walk;
        walkConsumer.accept("黄山路");*/

        /*Person person  = new Person();
        person.setId(2L);
        person.setName("张三1");
        person.setAge(22);
        Person person2  = new Person();
        person2.setId(2L);
        person2.setName("张三2");
        person2.setAge(222);
        Person person3  = new Person();
        person3.setId(3L);
        person3.setName("张三3");
        person3.setAge(224);
        List<Person> list = new ArrayList<>();
        list.add(person);
        list.add(person2);
        list.add(person3);
//        Map<Long, Person> map = list.stream().collect(HashMap::new, (m, v) -> m.put(v.getId(), v), HashMap::putAll);
        Map<Long, String> map = list.stream().collect(Collectors.toMap(Person::getId, Person::getName, (oldValue, newValue) -> newValue));
//        map.forEach((k, v) -> System.out.println(k + ": " + v));
        Map<Boolean, List<Person>> m = list.stream().collect(Collectors.partitioningBy(k -> k.getId() >= 2));
        Hashtable<Object,Object> ht = System.getProperties();
        Set<Map.Entry<Object,Object>> set = ht.entrySet();
        System.out.println(ht);*/
    }
}
