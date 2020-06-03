package com.boot.mytt.core.spel;

import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;

import java.lang.reflect.Method;

public class MethodArgs {

    private LocalVariableTableParameterNameDiscoverer discoverer = new LocalVariableTableParameterNameDiscoverer();

    private ParameterNameDiscoverer parameterNameDiscoverer;


    public void test(Method method){
        String[] paramNames = this.parameterNameDiscoverer.getParameterNames(method);
    }
}
