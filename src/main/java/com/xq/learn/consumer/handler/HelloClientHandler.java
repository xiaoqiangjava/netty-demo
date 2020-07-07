package com.xq.learn.consumer.handler;

import com.xq.learn.consumer.ResultHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

public class HelloClientHandler extends ChannelInboundHandlerAdapter {
    Map<String, ResultHandler> map = new HashMap<>();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 收到服务端数据
        String result = msg.toString();
        System.out.println(result);
        ResultHandler resultHandler = map.get("result");
        resultHandler.setResponse(result);
        map.put("result", resultHandler);
    }

    public String getResponse() throws InterruptedException {
        return map.get("result").getResponse();
    }

    public void setResultHandler(ResultHandler resultHandler) {
        map.put("result", resultHandler);
    }
}
