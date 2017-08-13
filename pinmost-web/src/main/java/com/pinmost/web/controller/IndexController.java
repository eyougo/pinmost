package com.pinmost.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mei on 17/04/2017.
 */
@Controller
@RequestMapping
public class IndexController {

    @RequestMapping("/surface")
    public String surface()  {
        return "surface.ftl";
    }

    @RequestMapping({"/index", "/"})
    public String index(){
        return "most_new.ftl";
    }

    @RequestMapping({"/most_click"})
    public String mostClick(){
        return "most_click.ftl";
    }
}
