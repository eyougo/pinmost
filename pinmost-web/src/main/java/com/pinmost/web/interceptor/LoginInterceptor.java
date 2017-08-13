package com.pinmost.web.interceptor;

import com.pinmost.web.controller.BaseController;
import com.pinmost.web.model.Account;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * User: mei
 * Date: 9/9/13
 * Time: 11:34
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contextPath = request.getContextPath();
        HttpSession session = request.getSession();
        String redirect = URLEncoder.encode(request.getPathInfo() +
                ( StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString()), "UTF-8");
        try {
            Account account = (Account) session.getAttribute(BaseController.ACCOUNT_SESSION_KEY);
            if (account == null){
                response.sendRedirect(contextPath + "/login?redirect="+redirect);
                return false;
            }
            return true;
        } catch (Exception e){
            response.sendRedirect(contextPath + "/login?redirect="+redirect);
            return false;
        }
    }
}
