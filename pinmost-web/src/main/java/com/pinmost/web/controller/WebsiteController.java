package com.pinmost.web.controller;

import com.eyougo.common.result.BooleanResult;
import com.eyougo.common.result.DataResult;
import com.eyougo.common.result.RangeDataResult;
import com.pinmost.web.model.Account;
import com.pinmost.web.model.Website;
import com.pinmost.web.model.WebsiteAccount;
import com.pinmost.web.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.standard.DateTimeContext;
import org.springframework.format.datetime.standard.DateTimeContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.ZoneId;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by mei on 23/03/2017.
 */
@Controller
@RequestMapping
public class WebsiteController extends BaseController{

    @Autowired
    private WebsiteService websiteService;

    @RequestMapping(value = "/pin")
    public String pin(){
        return "pin.ftl";
    }


    @RequestMapping(value = "/pinSubmit")
    public String pinSubmit(@ModelAttribute Website website, HttpSession session, RedirectAttributes redirectAttributes, Locale locale){
        Account account = getAccountFromSession(session);
        BooleanResult booleanResult = websiteService.doCreateWebsite(website, account.getId());
        if (booleanResult.getSuccess()) {
            return "redirect:/";
        } else {
            redirectAttributes.addFlashAttribute("errorResult", booleanResult.localizeMessage(messageSource, locale));
            return "redirect:/pin";
        }
    }

    @RequestMapping(value = "/click")
    public String click(@RequestParam Integer id){
        DataResult<Website> dataResult = websiteService.doClickWebsite(id);
        if (dataResult.getSuccess()) {
            return "redirect:" + dataResult.getData().getUrl();
        } else {
            return "redirect:/";
        }
    }

    @RequestMapping("/getUrl")
    @ResponseBody
    public DataResult<Website> get(@RequestParam String url) {
        return websiteService.getWebsite(url);
    }

    @RequestMapping("/mostNew")
    @ResponseBody
    public RangeDataResult<WebsiteAccount> mostNewList(@RequestParam int offset){
        return websiteService.getMostNewList(offset);
    }

    @RequestMapping("/mostClick")
    @ResponseBody
    public RangeDataResult<WebsiteAccount> mostClickList(@RequestParam int offset){
        return websiteService.getMostClickList(offset);
    }

}
