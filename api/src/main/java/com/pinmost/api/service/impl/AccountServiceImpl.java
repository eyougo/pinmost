package com.pinmost.api.service.impl;

import com.pinmost.api.model.Account;
import com.pinmost.api.service.AccountService;
import org.springframework.stereotype.Service;

/**
 * Created by mei on 29/03/2017.
 */
@Service
public class AccountServiceImpl implements AccountService{

    @Override
    public Account validateLogin(String login, String password) {
        return null;
    }

    @Override
    public Account validateToken(String token) {
        return null;
    }
}
