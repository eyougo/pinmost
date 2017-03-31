package com.pinmost.api.dao;

import com.pinmost.api.model.Account;

/**
 * Created by mei on 24/03/2017.
 */
public interface AccountDao {

    Account getByLogin(String login);

    int createAccount(Account account);
}
