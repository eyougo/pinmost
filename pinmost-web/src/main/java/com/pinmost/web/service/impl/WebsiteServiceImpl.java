package com.pinmost.web.service.impl;

import com.eyougo.common.result.BooleanResult;
import com.eyougo.common.result.Pager;
import com.eyougo.common.result.RangeDataResult;
import com.pinmost.web.dao.mapper.WebsiteCollectMapper;
import com.pinmost.web.dao.mapper.WebsiteMapper;
import com.pinmost.web.model.Website;
import com.pinmost.web.model.WebsiteAccount;
import com.pinmost.web.model.WebsiteCollect;
import com.pinmost.web.service.WebsiteService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

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
}
