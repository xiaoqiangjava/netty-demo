package com.xq.learn.provider.bootstrap;

import com.xq.learn.provider.server.NettyServer;

/**
 * netty服务端启动类
 */
public class ServerBootstrap {
    public static void main(String[] args) throws InterruptedException {
        NettyServer.startServer("localhost", 58088);
    }
}
