package com.pinmost.web.service;

import com.eyougo.common.result.DataResult;
import com.pinmost.web.model.Account;

/**
 * Created by mei on 24/03/2017.
 */
public interface AccountService {

    DataResult<Account> doValidateLogin(String login, String password);

    DataResult<Account> doCreateAccount(Account account);
}
