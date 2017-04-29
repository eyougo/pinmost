package com.pinmost.api.service.impl;

import com.pinmost.api.common.exception.CustomerException;
import com.pinmost.api.dao.AccountDao;
import com.pinmost.api.model.Account;
import com.pinmost.api.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mei on 29/03/2017.
 */
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountDao accountDao;

    @Override
    public Account validateLogin(String login, String password) throws CustomerException {
        Account account = accountDao.getByLogin(login);
        if (account == null) {
            throw new CustomerException("account login:" + login + " can't find", "account.login.error.login", null);
        }
        String mySqlPassword = accountDao.getMySQLPassword(password);
        if (StringUtils.equals(account.getPassword(), mySqlPassword)) {
            return account;
        } else {
            throw new CustomerException("account login:" + login + ", password:" + password + " not correct", "account.login.error.password", null);
        }
    }

    @Override
    public Account validateToken(String token) {
        return null;
    }

    @Override
    public Account create(Account account) throws CustomerException {
        if (accountDao.getCountByEmail(account.getEmail()) > 0) {
            throw new CustomerException("create account email:" + account.getEmail() + " is duplicate", "account.register.error.email", null);
        }
        if (accountDao.getCountByUsername(account.getUsername()) > 0) {
            throw new CustomerException("create account username:" + account.getUsername() + " is duplicate", "account.register.error.username", null);
        }
        //TODO 校验要求username字母和数字组合
        if (StringUtils.isBlank(account.getUsername())) {
            throw new CustomerException("create account username:" + account.getUsername() + " is duplicate", "account.register.error.username", null);
        }
        int id = accountDao.createAccount(account);
        account.setId(id);
        return account;
    }

    @Override
    public Account getByUsername(String username) {
        return accountDao.getByUsername(username);
    }
}
