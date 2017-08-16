package com.pinmost.web.service;

import com.eyougo.common.result.BooleanResult;
import com.eyougo.common.result.DataResult;
import com.eyougo.common.result.RangeDataResult;
import com.pinmost.web.model.Website;
import com.pinmost.web.model.WebsiteAccount;

/**
 * Created by mei on 19/04/2017.
 */
public interface WebsiteService {

    RangeDataResult<WebsiteAccount> getMostNewList(int offset);

    RangeDataResult<WebsiteAccount> getMostClickList(int offset);

    RangeDataResult<Website> getAccountPinList(Integer accountId, int offset);

    BooleanResult doCreateWebsite(Website website, Integer accountId);

    DataResult<Website> getWebsite(String url);

    DataResult<Website> doClickWebsite(Integer id);
}
