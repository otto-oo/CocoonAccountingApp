package com.cocoon.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogExecutionInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            if (handler instanceof HandlerMethod){
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                request.setAttribute("requestStartTime", System.currentTimeMillis());
                log.info("Starting Method controller method: {}.{} by {}", handlerMethod.getMethod().getName(), handlerMethod.getBeanType().getSimpleName() ,username);
            }
        }catch (Exception e){
            log.error("Caught exception while executing method", e);
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        try {
            if (handler instanceof HandlerMethod){
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                log.info("Completed controller method for {}.{} takes {} ms." , handlerMethod.getMethod().getName(), handlerMethod.getBeanType().getSimpleName() ,(System.currentTimeMillis() - (Long) request.getAttribute("requestStartTime")));
            }
        }catch (Exception e){
            log.error("Caught an exception while executing handler method", e);
        }
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
