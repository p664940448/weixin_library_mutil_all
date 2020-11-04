package com.netmax.library.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 

import com.jfinal.handler.Handler;

/**
 * 允许跨域访问
 * @author John
 *
 */
public class BaseHandle extends Handler {
 
    @SuppressWarnings("deprecation")
	@Override
    public void handle(String target, HttpServletRequest request,
            HttpServletResponse response, boolean[] isHandled) {
         
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type");
        response.addHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS");
        next.handle(target, request, response, isHandled);
    }
 
}