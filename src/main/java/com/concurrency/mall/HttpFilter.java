package com.concurrency.mall;

import com.concurrency.mall.example.threadLocal.RequestHolder;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @description: Filter主要对用户请求进行预处理，接着将请求交给servlet进行响应。
 * @author: marcus
 * @date: 2018/11/1
 */
@Slf4j
public class HttpFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /**一般先转换为HttpServletRequest,然后从session中根据名字获取值 */

        HttpServletRequest request = (HttpServletRequest)servletRequest;
        //request.getSession().getAttribute("userName");

        log.info("do filter {}, {}",Thread.currentThread().getId(),request.getServletPath());
        RequestHolder.add(Thread.currentThread().getId());

        /**处理完之后，让请求继续 */
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
