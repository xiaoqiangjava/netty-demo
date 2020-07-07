package com.xq.learn.consumer.client;

import com.xq.learn.consumer.ResultHandler;
import com.xq.learn.consumer.handler.HelloClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.concurrent.ExecutionException;

public class NettyClient {
    private Channel channel;

    private HelloClientHandler clientHandler;

    public void connect() throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        clientHandler = new HelloClientHandler();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        // 添加编码器
                        pipeline.addLast(new StringEncoder())
                                // 添加解码器
                                .addLast(new StringDecoder())
                                // 请求处理类
                                .addLast(clientHandler);
                    }
                });
        channel = bootstrap.connect("localhost", 58088).sync().channel();
    }

    public String sendMsg(String msg) throws InterruptedException, ExecutionException {
        channel.writeAndFlush(msg).sync();
        clientHandler.setResultHandler(new ResultHandler());
        return clientHandler.getResponse();
    }
}
