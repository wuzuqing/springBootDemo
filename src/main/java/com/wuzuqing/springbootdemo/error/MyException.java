package com.wuzuqing.springbootdemo.error;

public class MyException extends Exception {
    public MyException() {
    }

    public MyException(String message) {
        super(message);
    }
}
