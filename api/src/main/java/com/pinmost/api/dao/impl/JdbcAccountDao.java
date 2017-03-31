package com.pinmost.api.dao.impl;

import com.pinmost.api.common.dao.JdbcBaseDao;
import com.pinmost.api.dao.AccountDao;
import com.pinmost.api.model.Account;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

/**
 * Created by mei on 31/03/2017.
 */
@Repository
public class JdbcAccountDao extends JdbcBaseDao implements AccountDao{
    @Override
    public Account getByLogin(String login) {
        String sql = "select * from account where email = ? or mobile = ?";
        List<Account> accounts = this.getJdbcTemplate().query(sql, (rs, rowNum) -> {
            Account account = new Account();
            account.setId(rs.getInt("id"));
            account.setUsername(rs.getString("username"));
            account.setEmail(rs.getString("email"));
            account.setMobile(rs.getString("mobile"));
            account.setPassword(rs.getString("password"));
            account.setUpdatedAt(rs.getDate("updated_at"));
            account.setCreatedAt(rs.getDate("created_at"));
            return account;
        });
        if (accounts.size() > 0){
            return accounts.get(0);
        }
        return null;
    }

    @Override
    public int createAccount(Account account) {
        String sql = "insert into account (username, email, mobile, password) values (?, ?, ?, PASSWORD(?))";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        this.getJdbcTemplate().update(con -> {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getEmail());
            statement.setString(3, account.getMobile());
            statement.setString(4, account.getPassword());
            return statement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }
}
