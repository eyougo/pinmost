package com.pinmost.api.web.controller;

import com.pinmost.api.model.Account;
import com.pinmost.api.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by mei on 05/04/2017.
 */
@Controller
@RequestMapping
public class PersonController {

    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String person(@PathVariable String username, Model model)  {
        Account account = accountService.getByUsername(username);
        model.addAttribute("account", account);
        return "person.ftl";
    }
}
