package com.pinmost.web.controller;

import com.eyougo.common.result.DataResult;
import com.pinmost.web.model.Account;
import com.pinmost.web.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
        DataResult<Account> dataResult = accountService.doValidateLogin(login, password);
        if (dataResult.getSuccess()) {
            session.setAttribute(ACCOUNT_SESSION_KEY, dataResult.getData());
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("errorResult", dataResult.localizeMessage(messageSource, locale));
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
    public String register(Account account, RedirectAttributes redirectAttributes, Locale locale, HttpSession session) {
        DataResult<Account> dataResult = accountService.doCreateAccount(account);
        if (dataResult.getSuccess()) {
            session.setAttribute(ACCOUNT_SESSION_KEY, dataResult.getData());
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("errorResult", dataResult.localizeMessage(messageSource, locale));
            return "redirect:/join";
        }
    }

}
