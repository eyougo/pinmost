package com.pinmost.web.service;

import com.pinmost.test.AbstractSpringTest;
import com.pinmost.web.model.Website;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by mei on 07/08/2017.
 */
public class WebsiteServiceTest extends AbstractSpringTest{

    @Autowired
    private WebsiteService websiteService;

    @Test
    public void getMostNewList() throws Exception {

    }

    @Test
    public void getMostClickList() throws Exception {

    }

    @Test
    public void doCreateWebsite() throws Exception {

        for (int i = 0; i < 100; i++) {
            Website website = new Website();
            website.setClickCount(i);
            Thread.sleep(2000);
            website.setTitle(i+"ddddasdfasdfasdfdasdfasdfasdf");
            website.setSummary(i+"adadfpyueqprqwnepghanfangasngapsdn asdfasdfanf  asdijfasijdfas adijfangphapdfjas asdjfasdjfaisdjf");
            website.setUrl("http://www.sdfs.com"+i);
            websiteService.doCreateWebsite(website, 1);
        }
    }

}