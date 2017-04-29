package com.pinmost.api.dao.impl;

import com.pinmost.api.common.dao.JdbcBaseDao;
import com.pinmost.api.dao.AccountDao;
import com.pinmost.api.model.Account;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by mei on 31/03/2017.
 */
@Repository("accountDao")
public class JdbcAccountDao extends JdbcBaseDao implements AccountDao{
    @Override
    public String getMySQLPassword(String password) {
        return this.getJdbcTemplate().queryForObject("select PASSWORD(?)", String.class, password);
    }

    @Override
    public Account getByLogin(String login) {
        String sql = "select * from account where email = ? or username = ?";
        List<Account> accounts = this.getJdbcTemplate().query(sql, new AccountRowMapper(), login, login);
        if (accounts.size() > 0){
            return accounts.get(0);
        }
        return null;
    }

    @Override
    public int createAccount(Account account) {
        String sql = "insert into account (username, email, password) values (?, ?, PASSWORD(?))";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        this.getJdbcTemplate().update(con -> {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, account.getUsername());
            statement.setString(2, account.getEmail());
            statement.setString(3, account.getPassword());
            return statement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public Account getByUsername(String username) {
        String sql = "select * from account where username = ?";
        List<Account> accounts = this.getJdbcTemplate().query(sql, new AccountRowMapper(), username);
        if (accounts.size() > 0){
            return accounts.get(0);
        }
        return null;
    }

    @Override
    public int getCountByEmail(String email) {
        String sql = "select count(*) from account where email = ?";
        return this.getJdbcTemplate().queryForObject(sql, Integer.class, email);
    }

    @Override
    public int getCountByUsername(String username) {
        String sql = "select count(*) from account where username = ?";
        return this.getJdbcTemplate().queryForObject(sql, Integer.class, username);
    }

    private class AccountRowMapper implements RowMapper<Account> {

        @Override
        public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
            Account account = new Account();
            account.setId(rs.getInt("id"));
            account.setUsername(rs.getString("username"));
            account.setEmail(rs.getString("email"));
            account.setPassword(rs.getString("password"));
            account.setUpdatedAt(rs.getDate("updated_at"));
            account.setCreatedAt(rs.getDate("created_at"));
            return account;
        }
    }
}
