package com.cloudST.security.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccessFilter  extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(authenticate(servletRequest)){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
            httpResponse.sendRedirect("/error");
            return;
        }


    }

    private boolean authenticate(ServletRequest servletRequest) {
        return true;
    }
}
