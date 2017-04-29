package com.pinmost.api.dao;

import com.pinmost.api.model.Website;

import java.util.List;

/**
 * Created by mei on 17/04/2017.
 */
public interface WebsiteDao {

    int getCountByUrlHash(String urlHash);

    int createWebsite(Website website);

    List<Website> getListOrderByRecent(int offset, int size);

    List<Website> getListOrderByCollectCount(int offset, int size);

    List<Website> getListByAccount(Integer accountId, int offset, int size);

    int getTotalCount();
}