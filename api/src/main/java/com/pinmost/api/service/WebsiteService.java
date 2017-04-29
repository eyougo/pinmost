package com.pinmost.api.service;

import com.pinmost.api.model.Website;
import com.pinmost.api.web.result.PageList;

/**
 * Created by mei on 19/04/2017.
 */
public interface WebsiteService {

    PageList<Website> getRecentList(int offset);

    void createWebsite(Website website);
}
