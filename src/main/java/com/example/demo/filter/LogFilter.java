package com.example.demo.filter;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


/**
 * @author zhaoyue
 * @description:
 * @create 2020/4/17 11:19 上午
 **/
@Slf4j
@Order(1)
@WebFilter(filterName = "logFilter", urlPatterns = "/*")
public class LogFilter implements Filter {

    @Value("${spring.application.name}")
    private String serverName;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        MDC.put("ip", request.getLocalAddr());
        MDC.put("serverName", serverName);
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
