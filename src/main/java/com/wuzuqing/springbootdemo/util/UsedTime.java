package com.wuzuqing.springbootdemo.util;

public class UsedTime {
    private long start;
    private long end;

    private static UsedTime usedTime = new UsedTime();

    public static UsedTime get(){
        return usedTime;
    }

    public void begin() {
        start = System.currentTimeMillis();
    }


    public void printUsedTime() {
        System.out.println("used time = " + usedTime());
    }

    private long usedTime() {
        return System.currentTimeMillis() - start;
    }
}
