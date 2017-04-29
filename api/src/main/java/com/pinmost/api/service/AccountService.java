package com.pinmost.api.service;

import com.pinmost.api.common.exception.CustomerException;
import com.pinmost.api.model.Account;

/**
 * Created by mei on 24/03/2017.
 */
public interface AccountService {

    Account validateLogin(String login, String password) throws CustomerException;

    Account validateToken(String token);

    Account create(Account account)throws CustomerException;

    Account getByUsername(String username);
}
