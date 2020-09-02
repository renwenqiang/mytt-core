package com.boot.mytt.core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author renwq
 * @since 2020/8/31 10:59
 */
public class Test01 {

    public static interface Foo{
        void test();
    }

    public static class MyInvocationHandler implements InvocationHandler{

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("proxy method");
            return null;
        }
    }

    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //设置saveGeneratedFiles值为true生成class字节码到文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        // 1.生成代理类，利用反射得到代理对象
        Class<?> proxyClass = Proxy.getProxyClass(Foo.class.getClassLoader(), new Class[]{Foo.class});
        Foo f = (Foo)proxyClass.getConstructor(InvocationHandler.class).newInstance(new MyInvocationHandler());
        f.test();
        // 2.直接获取代理对象
        Foo o = (Foo)Proxy.newProxyInstance(Foo.class.getClassLoader(), new Class[]{Foo.class}, new MyInvocationHandler());
        o.test();
    }
}
