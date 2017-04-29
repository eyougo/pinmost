package com.pinmost.api.service.impl;

import com.pinmost.api.dao.WebsiteDao;
import com.pinmost.api.model.Website;
import com.pinmost.api.service.WebsiteService;
import com.pinmost.api.web.result.PageList;
import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by mei on 19/04/2017.
 */
@Service
public class WebsiteServiceImpl implements WebsiteService{

    private static final int PAGE_SIZE = 20;

    @Autowired
    private WebsiteDao websiteDao;

    @Override
    public PageList<Website> getRecentList(int offset) {
        PageList<Website> pageList = new PageList<>();
        int total = websiteDao.getTotalCount();
        pageList.setTotal(total);
        List<Website> websiteList = websiteDao.getListOrderByRecent(offset, PAGE_SIZE);
        pageList.setList(websiteList);
        if (total > offset + PAGE_SIZE) {
            pageList.setNextStart(offset + PAGE_SIZE);
        } else {
            pageList.setNextStart(-1);
        }
        return pageList;
    }

    @Override
    public void createWebsite(Website website) {
        String url = website.getUrl();
        website.setUrlHash(DigestUtils.md5Hex(url));
        websiteDao.createWebsite(website);
    }
}
