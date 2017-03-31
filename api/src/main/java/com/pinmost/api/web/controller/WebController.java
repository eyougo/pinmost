package com.pinmost.api.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mei on 29/03/2017.
 */
@Controller
@RequestMapping
public class WebController {

    @RequestMapping("/surface")
    public String surface()  {
        return "surface.ftl";
    }

    @RequestMapping({"/index", "/"})
    public String index(){
        return "index.ftl";
    }

    @RequestMapping("/join")
    public String join()  {
        return "join.ftl.ftl";
    }


    @RequestMapping("/login")
    public String login()  {
        return "login.ftl";
    }

    @RequestMapping("/me")
    public String me()  {
        return "me.ftl";
    }
}
