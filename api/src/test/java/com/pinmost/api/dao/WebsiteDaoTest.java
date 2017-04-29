package com.pinmost.api.dao;

import com.pinmost.api.model.Website;
import com.pinmost.test.AbstractSpringTest;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by mei on 19/04/2017.
 */
public class WebsiteDaoTest extends AbstractSpringTest{

    @Autowired
    private WebsiteDao websiteDao;

    @Test
    public void getCountByUrlHash() throws Exception {

    }

    @Test
    public void createWebsite() throws Exception {
        Website website = new Website();
        website.setTitle("测试一大堆东西爱的方式的发顺丰");
        website.setSummary("测试一大堆东西爱的方式的发顺丰测试一大堆东西爱的方式的发顺丰测试一大堆东西爱的方式的发顺丰测试一大堆东西爱的方式的发顺丰测试一大堆东西爱的方式的发顺丰");
        website.setUrl("http://www.baidu.com");
        website.setUrlHash(DigestUtils.md5Hex(website.getUrl()));
        websiteDao.createWebsite(website);
    }

    @Test
    public void getListOrderByRecent() throws Exception {
        List<Website> websiteList = websiteDao.getListOrderByRecent(0, 20);
        System.out.println(websiteList);
    }

    @Test
    public void getListOrderByCollectCount() throws Exception {

    }

    @Test
    public void getListByAccount() throws Exception {

    }

    @Test
    public void getTotalCount() throws Exception {

    }

}