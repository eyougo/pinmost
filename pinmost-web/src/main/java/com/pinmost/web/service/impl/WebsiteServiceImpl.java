package com.pinmost.web.service.impl;

import com.eyougo.common.result.BooleanResult;
import com.eyougo.common.result.DataResult;
import com.eyougo.common.result.Pager;
import com.eyougo.common.result.RangeDataResult;
import com.pinmost.web.dao.http.HttpClientDao;
import com.pinmost.web.dao.mapper.WebsiteCollectMapper;
import com.pinmost.web.dao.mapper.WebsiteMapper;
import com.pinmost.web.model.Website;
import com.pinmost.web.model.WebsiteAccount;
import com.pinmost.web.model.WebsiteCollect;
import com.pinmost.web.service.WebsiteService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.HttpResponseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by mei on 04/08/2017.
 */
@Service("websiteService")
public class WebsiteServiceImpl implements WebsiteService{

    private static final int PAGE_SIZE = 20;

    @Autowired
    private WebsiteMapper websiteMapper;

    @Autowired
    private WebsiteCollectMapper websiteCollectMapper;

    @Autowired
    private HttpClientDao httpClientDao;

    @Override
    public RangeDataResult<WebsiteAccount> getMostNewList(int offset) {
        if (offset < 0) {
            offset = 0;
        }
        List<WebsiteAccount> websiteList = websiteMapper.selectOrderByCreatedAt(offset, PAGE_SIZE);

        int nextOffset;
        if (websiteList.isEmpty()) {
            nextOffset = -1;
        } else {
            nextOffset = offset + PAGE_SIZE;
        }

        int previousOffset = offset - PAGE_SIZE;
        Pager pager = new Pager(previousOffset, nextOffset, -1);
        return RangeDataResult.success(websiteList, pager);
    }

    @Override
    public RangeDataResult<WebsiteAccount> getMostClickList(int offset) {
        if (offset < 0) {
            offset = 0;
        }
        List<WebsiteAccount> websiteList = websiteMapper.selectOrderByClickCount(offset, PAGE_SIZE);

        int nextOffset;
        if (websiteList.isEmpty()) {
            nextOffset = -1;
        } else {
            nextOffset = offset + PAGE_SIZE;
        }

        int previousOffset = offset - PAGE_SIZE;
        Pager pager = new Pager(previousOffset, nextOffset, -1);
        return RangeDataResult.success(websiteList, pager);
    }

    @Override
    public BooleanResult doCreateWebsite(Website website, Integer accountId) {
        String urlHash = DigestUtils.md5Hex(website.getUrl());
        if (websiteMapper.selectCountByUrlHash(urlHash) > 0) {
            return BooleanResult.failed("website.create.error.urlhash");
        }
        website.setUrlHash(urlHash);
        website.setClickCount(0);
        try {
            websiteMapper.insertSelective(website);
        }catch (DuplicateKeyException e) {
            return BooleanResult.failed("website.create.error.urlhash");
        }
        WebsiteCollect websiteCollect = new WebsiteCollect();
        websiteCollect.setWebsiteId(website.getId());
        websiteCollect.setAccountId(accountId);
        websiteCollect.setFrom(true);
        websiteCollectMapper.insertSelective(websiteCollect);
        return BooleanResult.success();
    }

    @Override
    public DataResult<Website> getWebsite(String url) {
        String webContent;
        try {
            webContent = httpClientDao.get(url);
        } catch (IOException e) {
            return DataResult.failed("website.get.error.response_error");
        }

        if (StringUtils.isEmpty(webContent)) {
            return DataResult.failed("website.get.error.no_content");
        }

        Website website = new Website();
        website.setUrl(url);
        Document document = Jsoup.parse(webContent);
        String title = document.title();
        website.setTitle(title);
        Elements metas = document.head().select("meta");
        for (Element meta : metas) {
            if ("description".equalsIgnoreCase(meta.attr("name"))) {
                website.setSummary(meta.attr("content"));
            }
        }
        return DataResult.success(website);
    }

    @Override
    public DataResult<Website> doClickWebsite(Integer id) {
        Website website = websiteMapper.selectByPrimaryKey(id);
        if (website == null) {
            return DataResult.failed("website.click.error.not_found");
        }
        websiteMapper.updateClickCount(id);
        return DataResult.success(website);
    }
}
