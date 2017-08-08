package com.pinmost.web.service;

import com.eyougo.common.result.DataResult;
import com.pinmost.test.AbstractSpringTest;
import com.pinmost.web.model.Account;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mei on 04/08/2017.
 */
public class AccountServiceTest extends AbstractSpringTest{

    @Autowired
    private AccountService accountService;

    @Test
    public void validateLogin() throws Exception {
        DataResult<Account> dataResult = accountService.doValidateLogin("test@test.com", "123456");
        Assert.assertTrue(dataResult.getSuccess());
    }

    @Test
    public void create() throws Exception {

    }

    @Test
    public void getByUsername() throws Exception {

    }

}