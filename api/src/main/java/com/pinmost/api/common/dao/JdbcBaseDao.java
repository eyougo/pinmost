package com.pinmost.api.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * User: mei
 * Date: 2/2/16
 * Time: 10:40
 */
@Component
public abstract class JdbcBaseDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
