package com.xq.learn.consumer.proxy;

import com.xq.learn.consumer.client.NettyClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvocation implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 在这里通过netty实现远程方法调用
        NettyClient client = new NettyClient();
        client.connect();
        
        return client.sendMsg(args[0].toString());
    }
}
