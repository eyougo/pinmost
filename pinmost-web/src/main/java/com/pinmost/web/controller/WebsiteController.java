package com.pinmost.web.controller;

import com.eyougo.common.result.RangeDataResult;
import com.pinmost.web.model.Website;
import com.pinmost.web.model.WebsiteAccount;
import com.pinmost.web.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.format.datetime.standard.DateTimeContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.TimeZone;

/**
 * Created by mei on 23/03/2017.
 */
@Controller
@RequestMapping("/website")
public class WebsiteController {

    @Autowired
    private WebsiteService websiteService;

    @RequestMapping("/pin")
    public String pin(){
        return "pin.ftl";
    }

    @RequestMapping("/mostNew")
    @ResponseBody
    public RangeDataResult<WebsiteAccount> mostNewList(@RequestParam int offset, HttpServletRequest request){
        DateTimeContextHolder.setDateTimeContext(new DateTimeContext());
        return websiteService.getMostNewList(offset);
    }


}
