package com.concurrency.mall;

import com.concurrency.mall.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: Interceptor拦截器，在AOP中用于在某个方法或字段被访问之前或之后进行拦截，加入某些操作。
 * 拦截器是AOP实现的策略之一。
 * @author: marcus
 * @date: 2018/11/1
 */
@Slf4j
public class HttpInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        log.info("preHandle");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) throws Exception {
        RequestHolder.remove();
        log.info("afterCompletion");
    }
}
