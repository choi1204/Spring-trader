package com.example.springtrader.common.util;

public class ThreadUtil {

    public static void threadSleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
