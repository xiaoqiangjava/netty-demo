package com.xq.learn.provider.handler;

import com.xq.learn.provider.impl.HelloServiceImpl;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * netty handler，用于接受客户端请求，然后按照约定调用本地实现，返回结果
 */
public class HelloServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 调用业务代码
        String result = new HelloServiceImpl().hello(msg.toString());
        // 将调用结果通过ctx返回给客户端
        ctx.writeAndFlush(result);
    }
}
