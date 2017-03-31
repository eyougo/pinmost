package com.pinmost.api.web.controller;

import com.pinmost.api.model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by mei on 21/03/2017.
 */

@Controller
@RequestMapping("/account")
public class AccountController {

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Account register(@RequestBody Account account) {


        return null;
    }

    @RequestMapping(value = "/session", method = RequestMethod.POST)
    @ResponseBody
    public Account session(@RequestParam String login, @RequestParam String password, HttpSession session){


        return null;
    }
}
