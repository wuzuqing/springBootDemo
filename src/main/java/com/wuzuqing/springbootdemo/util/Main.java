package com.wuzuqing.springbootdemo.util;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int value = 3;
        String valueStr = String.format("%03d", value);
        System.out.println(valueStr+" / "+ Arrays.toString(valueStr.getBytes()));
    }

}
