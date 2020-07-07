package com.xq.learn.provider.impl;

import com.xq.learn.service.IHelloService;

/**
 * 具体的业务实现类，这个类服务消费者不能直接调用的到，只能通过动态代理的方式透明调用，底层使用netty来获取服务提供者返回的信息
 */
public class HelloServiceImpl implements IHelloService {
    public String hello(String msg) {
        return "I'm fine, thank you!";
    }
}
