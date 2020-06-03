package com.boot.mytt.core.annotation.aliasfor.usage2;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@AnnotationBase
public @interface AnnotationChild {

    @AliasFor(annotation = AnnotationBase.class, attribute = "value")
    String extendValue() default "";
}
