package com.wuzuqing.springbootdemo.baseresp;

import lombok.Data;

import java.util.List;

@Data
public class RespData<T> {
    private T data;
    private int code;
    private String msg;

    public RespData(T data) {
        if (data == null || (data instanceof List && ((List) data).isEmpty())) {
            setParams(null, -1, "获取失败");
        } else {
            setParams(data, 100, "获取成功");
        }
    }

    public RespData(int code, String msg) {
        setParams(null, code, msg);
    }

    public void setParams(T data, int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


    public static <T> RespData<T> success(T data) {
        return new RespData(data);
    }

    public static RespData filed(int code, String msg) {
        return new RespData(code, msg);
    }

}