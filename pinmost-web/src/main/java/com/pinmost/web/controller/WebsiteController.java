package com.pinmost.web.controller;

import com.eyougo.common.result.BooleanResult;
import com.eyougo.common.result.DataResult;
import com.eyougo.common.result.RangeDataResult;
import com.pinmost.web.model.Account;
import com.pinmost.web.model.Website;
import com.pinmost.web.model.WebsiteAccount;
import com.pinmost.web.service.AccountService;
import com.pinmost.web.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by mei on 23/03/2017.
 */
@Controller
@RequestMapping
public class WebsiteController extends BaseController{

    @Autowired
    private WebsiteService websiteService;

    @Autowired
    private AccountService accountService;

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

    @RequestMapping({"/index", "/"})
    public String index(Model model){
        RangeDataResult<WebsiteAccount> dataResult = websiteService.getMostNewList(0);
        model.addAttribute("dataResult", dataResult);
        return "/most_new.ftl";
    }

    @RequestMapping("/most_new/{offset}")
    public String mostNewList(@PathVariable(required = false) Integer offset, Model model){
        if (offset == null) {
            offset = 0;
        }
        RangeDataResult<WebsiteAccount> dataResult = websiteService.getMostNewList(offset);
        model.addAttribute("dataResult", dataResult);
        return "/most_new.ftl";
    }
    @RequestMapping("/most_click")
    public String mostClick(Model model){
        RangeDataResult<WebsiteAccount> dataResult = websiteService.getMostClickList(0);
        model.addAttribute("dataResult", dataResult);
        return "/most_click.ftl";
    }

    @RequestMapping("/most_click/{offset}")
    public String mostClickList(@PathVariable(required = false) Integer offset, Model model){
        if (offset == null) {
            offset = 0;
        }
        RangeDataResult<WebsiteAccount> dataResult = websiteService.getMostClickList(offset);
        model.addAttribute("dataResult", dataResult);
        return "/most_click.ftl";
    }

    @RequestMapping("/{username}")
    public String person(@PathVariable String username, HttpSession session, Model model) {
        DataResult<Account> accountDataResult = accountService.getAccountByUsername(username);
        if (!accountDataResult.getSuccess()) {
            return "redirect:/";
        }
        Account account = accountDataResult.getData();
        model.addAttribute("account", account);
        Account sessionAccount = this.getAccountFromSession(session);
        if (sessionAccount != null) {
            model.addAttribute("sessionAccount", sessionAccount);
        }
        RangeDataResult<Website> dataResult = websiteService.getAccountPinList(account.getId(), 0);
        model.addAttribute("dataResult", dataResult);
        return "/person.ftl";
    }

    @RequestMapping("/{username}/{offset}")
    public String personList(@PathVariable String username, @PathVariable(required = false) int offset, HttpSession session, Model model){
        DataResult<Account> accountDataResult = accountService.getAccountByUsername(username);
        if (!accountDataResult.getSuccess()) {
            return "redirect:/";
        }
        Account account = accountDataResult.getData();
        model.addAttribute("account", account);
        Account sessionAccount = this.getAccountFromSession(session);
        if (sessionAccount != null) {
            model.addAttribute("sessionAccount", sessionAccount);
        }
        RangeDataResult<Website> dataResult = websiteService.getAccountPinList(account.getId(), offset);
        model.addAttribute("dataResult", dataResult);
        return "/person.ftl";
    }
}
