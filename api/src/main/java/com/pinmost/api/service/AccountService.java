package com.pinmost.api.service;

import com.pinmost.api.model.Account;

/**
 * Created by mei on 24/03/2017.
 */
public interface AccountService {

    Account validateLogin(String login, String password);

    Account validateToken(String token);
}
