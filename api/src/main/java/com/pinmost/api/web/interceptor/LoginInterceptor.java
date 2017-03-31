package com.pinmost.api.web.interceptor;

import com.pinmost.api.model.Account;
import com.pinmost.api.service.AccountService;
import com.pinmost.api.web.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * User: mei
 * Date: 9/9/13
 * Time: 11:34
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AccountService accountService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String contextPath = request.getContextPath();
        try {
            if ( ! validateLogin(request, response, handler)){
                response.sendRedirect(contextPath + "/index");
                return false;
            }
            return true;
        } catch (Exception e){
            response.sendRedirect(contextPath + "/index");
            return false;
        }
    }

    private boolean validateLogin(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute(BaseController.ACCOUNT_SESSION_KEY);
        if (account == null) {
            account = loginFromCookie(request, response);
            if (account == null) {
                return false;
            }
            session.setAttribute(BaseController.ACCOUNT_SESSION_KEY, account);
        }
        return true;
    }

    private Account loginFromCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();
        // check cookie
        String token = getCookieValue(cookies, BaseController.AUTH_COOKIE_TOKEN, null);
        if (token == null) {
            // not login yet
            return null;
        }

        try {
            Account account = accountService.validateToken(token);
            return account;
        } catch (Exception e) {
            // not valid cookie string
            Cookie cookie = new Cookie(BaseController.AUTH_COOKIE_TOKEN, null);
            cookie.setMaxAge(0);
            cookie.setDomain(BaseController.COOKIE_DOMAIN);
            cookie.setPath("/");
            response.addCookie(cookie);

            return null;
        }
    }

    private String getCookieValue(Cookie[] cookies, String cookieName,
                                  String defaultValue) {
        if (cookies == null)
            return null;

        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookieName.equals(cookie.getName()))
                return (cookie.getValue());
        }
        return (defaultValue);
    }
}
