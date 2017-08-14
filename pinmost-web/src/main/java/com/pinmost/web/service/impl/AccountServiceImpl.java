package com.pinmost.web.service.impl;

import com.eyougo.common.dao.mapper.BaseModelMapper;
import com.eyougo.common.result.DataResult;
import com.pinmost.web.dao.mapper.AccountMapper;
import com.pinmost.web.model.Account;
import com.pinmost.web.service.AccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

/**
 * Created by mei on 29/03/2017.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private BaseModelMapper baseModelMapper;

    @Override
    public DataResult<Account> doValidateLogin(String login, String password){
        Account account = accountMapper.selectByLogin(login);
        if (account == null) {
            return DataResult.failed("account.login.error.login");
        }
        String mySqlPassword = baseModelMapper.encryptPassword(password);
        if (!StringUtils.equals(account.getPassword(), mySqlPassword)) {
            return DataResult.failed("account.login.error.password");
        }
        return DataResult.success(account);
    }

    @Override
    public DataResult<Account> doCreateAccount(Account account) {
        if (accountMapper.selectCountByEmail(account.getEmail()) > 0) {
            return DataResult.failed("account.register.error.email");
        }
        if (accountMapper.selectCountByUsername(account.getUsername()) > 0) {
            return DataResult.failed("account.register.error.username");
        }
        if (StringUtils.isBlank(account.getUsername())) {
            return DataResult.failed("account.register.error.username");
        }
        try {
            accountMapper.insertSelective(account);
        }catch (DuplicateKeyException e) {
            return DataResult.failed("account.register.error.duplicate");
        }
        Account newAccount = accountMapper.selectByPrimaryKey(account.getId());
        return DataResult.success(newAccount);
    }
}
