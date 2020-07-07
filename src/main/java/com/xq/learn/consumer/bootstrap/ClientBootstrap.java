package com.xq.learn.consumer.bootstrap;

import com.xq.learn.consumer.proxy.ProxyUtil;
import com.xq.learn.service.IHelloService;

public class ClientBootstrap {
    public static void main(String[] args) throws InterruptedException {
        IHelloService service = ProxyUtil.getProxy(IHelloService.class);
        String result = service.hello("Are you ok?");
        System.out.println(result);
    }
}
