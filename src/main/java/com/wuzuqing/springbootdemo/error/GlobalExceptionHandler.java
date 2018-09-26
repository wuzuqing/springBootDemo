package com.wuzuqing.springbootdemo.error;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice("com.wuzuqing.springbootdemo.controller")
public class GlobalExceptionHandler {


    public static final String DEFAULT_ERROR_VIEW = "error";
    @ExceptionHandler(value = MyException.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }


    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Map<String,Object> errorResult( Exception e){
        HashMap<String, Object> map = new HashMap<>();
        map.put("errorCode","500");
        map.put("errorMsg","系统错误");
        return map;
    }

}
