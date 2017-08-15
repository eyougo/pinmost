package com.pinmost.web.controller;

import com.pinmost.web.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpSession;

/**
 * @author mei
 *
 */
public abstract class BaseController {
    public static final String AUTH_COOKIE_TOKEN = "pinmost_token";
    public static final String COOKIE_DOMAIN = ".pinmost.com";
    public static final String ACCOUNT_SESSION_KEY = "account";

	protected MessageSource messageSource;

	public Account getAccountFromSession(HttpSession session){
        return (Account) session.getAttribute(ACCOUNT_SESSION_KEY);
    }

	@Autowired
	@Required
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
