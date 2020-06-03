package com.boot.mytt.core.annotation.aliasfor.usage2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotatedElementUtils;
@Slf4j
public class TestExtend {
    public static void main(String[] args) {
        AnnotationBase base = AnnotatedElementUtils.findMergedAnnotation(ExtendClass.class, AnnotationBase.class);
        log.info(base.value());
    }
}
