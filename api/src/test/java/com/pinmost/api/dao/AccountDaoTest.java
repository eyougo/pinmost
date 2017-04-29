package com.pinmost.api.dao;

import com.pinmost.api.model.Account;
import com.pinmost.test.AbstractSpringTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * Created by mei on 31/03/2017.
 */
public class AccountDaoTest extends AbstractSpringTest{

    @Autowired
    private AccountDao accountDao;

    @Test
    public void testCreateAndLogin() throws Exception {
        String username = RandomStringUtils.randomAscii(6);
        String phone = RandomStringUtils.randomNumeric(13);
        Account account = new Account();
        account.setUsername(username);
        account.setEmail(username + "@test.com");
        account.setPassword("123456");
        int id = accountDao.createAccount(account);
        System.out.println(id);

        account = accountDao.getByLogin(username + "@test.com");
        assertEquals(id, account.getId().intValue());
        account = accountDao.getByLogin(phone);
        assertEquals(id, account.getId().intValue());
    }

}