package com.wuzuqing.springbootdemo.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Component
public class WebLogAspect {
    Logger logger  = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.wuzuqing.springbootdemo.controller.*.*(..))")
    public void webLog(){

    }

    /**
     *  前置通知
     * @param joinPoint
     * @throws Throwable
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("URL : "+request.getRequestURL().toString());
        logger.info("HTTP_METHOD : "+request.getMethod());
        logger.info("IP : "+request.getRemoteAddr());
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()){
            String name = enu.nextElement();
            logger.info("name:{},value:{}",name,request.getParameter(name));
        }
    }

    @AfterReturning(returning = "ret" ,pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable{
        logger.info("RESPONSE : "+ret);
    }
}
