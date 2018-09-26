package com.wuzuqing.springbootdemo.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AServer {


    @Async
    public String getEmailAndPhone() {
        log.info("2");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("3");
        return "1052243597@qq.com";
    }
}
