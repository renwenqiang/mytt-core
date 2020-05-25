package com.boot.mytt.core.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author renwq
 * @date 2020/5/25
 */
@WebFilter(filterName = "corsFilter", urlPatterns = "/*")
public class CorsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException,
            ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String origin = request.getHeader("origin");
        response.setHeader("Access-Control-Allow-Origin",origin);
        System.out.println(request.getRequestURL());
        filterChain.doFilter(request, servletResponse);
    }
}
