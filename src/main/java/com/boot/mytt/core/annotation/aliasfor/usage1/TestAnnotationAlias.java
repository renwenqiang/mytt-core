package com.boot.mytt.core.annotation.aliasfor.usage1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.Assert;

import java.util.function.Consumer;
@Slf4j
public class TestAnnotationAlias {
    public static void main(String[] args) throws NoSuchMethodException {
        Consumer<MyAliasAnnotation> logic = a -> {
            Assert.isTrue("这是值".equals(a.value()));
            Assert.isTrue(a.value().equals(a.location()));
            log.info("value: {} & location: {}", a.value(), a.location());
        };

        MyAliasAnnotation aliasAnnotation = AnnotationUtils.findAnnotation(MyClass.class.getMethod("one"), MyAliasAnnotation.class);
        logic.accept(aliasAnnotation);

//        MyAliasAnnotation aliasAnnotation2 = AnnotationUtils.findAnnotation(MyClass.class.getMethod("two"), MyAliasAnnotation.class);
//        logic.accept(aliasAnnotation2);
    }
}
