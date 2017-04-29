package com.pinmost.api.web.controller;

import com.pinmost.api.common.exception.CustomerException;
import com.pinmost.api.model.Account;
import com.pinmost.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by mei on 21/03/2017.
 */

@Controller
@RequestMapping
public class AccountController extends BaseController{

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login()  {
        return "login.ftl";
    }

    @RequestMapping(value = "/session", method = RequestMethod.POST)
    public String session(@RequestParam String login, @RequestParam String password, HttpSession session,
                          RedirectAttributes redirectAttributes, Locale locale){
        try {
            Account account = accountService.validateLogin(login, password);
            session.setAttribute(ACCOUNT_SESSION_KEY, account);
            return "redirect:/"+account.getUsername();
        } catch (CustomerException e) {
            redirectAttributes.addFlashAttribute("errorInfo", messageSource.getMessage(e.getErrorCode(), e.getStubParams(), locale));
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(ACCOUNT_SESSION_KEY);
        return "redirect:/";
    }

    @RequestMapping(value = "/join", method = RequestMethod.GET)
    public String join()  {
        return "join.ftl";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Account account, RedirectAttributes redirectAttributes, Locale locale, HttpSession session)  {
        try {
            account = accountService.create(account);
            session.setAttribute(ACCOUNT_SESSION_KEY, account);
            return "redirect:/"+account.getUsername();
        } catch (CustomerException e) {
            redirectAttributes.addFlashAttribute("errorInfo", messageSource.getMessage(e.getErrorCode(), e.getStubParams(), locale));
            return "redirect:/join";
        }
    }

}
