package com.pinmost.web.controller;

import com.eyougo.common.result.RangeDataResult;
import com.pinmost.web.model.WebsiteAccount;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by mei on 17/04/2017.
 */
@Controller
@RequestMapping
public class SurfaceController {

    @RequestMapping("/surface")
    public String surface()  {
        return "surface.ftl";
    }

}
