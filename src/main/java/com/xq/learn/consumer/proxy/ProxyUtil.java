package com.xq.learn.consumer.proxy;

import java.lang.reflect.Proxy;

public class ProxyUtil {
    private static ProxyInvocation invocation;
    public static <T> T getProxy(Class<T> cls) {
        invocation = new ProxyInvocation();
        return (T) Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{cls}, invocation);
    }
}
