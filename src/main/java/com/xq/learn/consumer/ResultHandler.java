package com.xq.learn.consumer;

import java.util.concurrent.CountDownLatch;

public class ResultHandler
{
    private String result;

    private volatile boolean isSuccess = false;

    CountDownLatch countDownLatch = new CountDownLatch(1);

    public String getResponse() throws InterruptedException {
        while (!isSuccess) {
            countDownLatch.await();
        }
        return result;
    }

    public void setResponse(String response) {
        this.result = response;
        this.isSuccess = true;
        countDownLatch.countDown();
    }
}
