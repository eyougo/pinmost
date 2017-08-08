package com.pinmost.web.service;

import com.eyougo.common.result.BooleanResult;
import com.eyougo.common.result.RangeDataResult;
import com.pinmost.web.model.Website;
import com.pinmost.web.model.WebsiteAccount;

/**
 * Created by mei on 19/04/2017.
 */
public interface WebsiteService {

    RangeDataResult<WebsiteAccount> getMostNewList(int offset);

    RangeDataResult<WebsiteAccount> getMostClickList(int offset);

    BooleanResult doCreateWebsite(Website website, Integer accountId);
}
