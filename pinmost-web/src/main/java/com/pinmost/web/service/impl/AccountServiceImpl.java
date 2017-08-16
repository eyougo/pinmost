package com.pinmost.web.service.impl;

import com.eyougo.common.dao.mapper.BaseModelMapper;
import com.eyougo.common.result.BooleanResult;
import com.eyougo.common.result.DataResult;
import com.pinmost.web.dao.mapper.AccountMapper;
import com.pinmost.web.model.Account;
import com.pinmost.web.service.AccountService;
import com.pinmost.web.util.ReservedUsernames;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
        try {
            accountMapper.insertSelective(account);
        }catch (DuplicateKeyException e) {
            return DataResult.failed("account.register.error.duplicate");
        }
        Account newAccount = accountMapper.selectByPrimaryKey(account.getId());
        return DataResult.success(newAccount);
    }

    @Override
    public BooleanResult checkUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return BooleanResult.failed("account.register.error.username_blank");
        }
        if (!StringUtils.isAlphanumeric(username) || !StringUtils.isAsciiPrintable(username)){
            return BooleanResult.failed("account.register.error.username_not_alphanumeric");
        }
        if (ReservedUsernames.isReservedUsername(username)){
            return BooleanResult.failed("account.register.error.username_reserved");
        }
        if (accountMapper.selectCountByUsername(username) > 0) {
            return BooleanResult.failed("account.register.error.username_duplicate");
        }
        return BooleanResult.success();
    }

    @Override
    public DataResult<Account> getAccountByUsername(String username) {
        Account account = accountMapper.selectByUsername(username);
        if (account == null) {
            return DataResult.failed("account.get.error.username_not_exist");
        }
        return DataResult.success(account);
    }
}
