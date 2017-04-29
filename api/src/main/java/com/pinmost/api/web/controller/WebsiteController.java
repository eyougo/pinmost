package com.pinmost.api.web.controller;

import com.pinmost.api.model.Website;
import com.pinmost.api.service.WebsiteService;
import com.pinmost.api.web.result.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mei on 23/03/2017.
 */
@Controller
@RequestMapping("/website")
public class WebsiteController {

    @Autowired
    private WebsiteService websiteService;

    @RequestMapping("/recent")
    @ResponseBody
    public PageList<Website> recent(@RequestParam int offset){
        return websiteService.getRecentList(offset);

    }


}
