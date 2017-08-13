package com.pinmost.web.dao.mapper;

import com.eyougo.common.dao.BaseMapper;
import com.pinmost.web.model.Account;

public interface AccountMapper extends BaseMapper<Account> {

    Account selectByLogin(String login);

    int selectCountByEmail(String email);

    int selectCountByUsername(String username);

}