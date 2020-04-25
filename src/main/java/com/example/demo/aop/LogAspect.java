package com.example.demo.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaoyue
 * @description:
 * @create 2020/4/24 1:34 下午
 **/
@Aspect
@Order(5)
@Component
@Slf4j
public class LogAspect {

    @Pointcut("execution(public * com.example.demo.controller..*(..))")
    public void weblog() {
    }

    /**
     * 请求执行过程 记录ip、入参、出参、耗时
     *
     * @param joinPoint
     */
    @Around("weblog()")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        HttpServletRequest request = getRequestContext();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        try {
            Object result = joinPoint.proceed(args);
            stopWatch.stop();
            if (log.isInfoEnabled()) {
                log.info("client_ip={},url={} ,cost_time={}ms,args={},result={}", request.getRemoteAddr(),
                        request.getRequestURL().toString(), stopWatch.getTotalTimeMillis(), JSON.toJSONString(args), JSON.toJSONString(result));
            }
            return result;
        } catch (Throwable e) {
            log.error("client_ip={},url={} ,cost_time={}ms,,args={}", request.getRemoteAddr(),
                    request.getRequestURL().toString(), stopWatch.getTotalTimeMillis(), JSON.toJSONString(args), e);
            throw e;
        }
    }

    /**
     * 获取HttpServletRequest
     *
     * @return
     */
    private HttpServletRequest getRequestContext() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        return request;
    }
}
