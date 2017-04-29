package com.pinmost.api.dao.impl;

import com.pinmost.api.common.dao.JdbcBaseDao;
import com.pinmost.api.dao.WebsiteDao;
import com.pinmost.api.model.Website;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by mei on 18/04/2017.
 */
@Repository("websiteDao")
public class JdbcWebsiteDao extends JdbcBaseDao implements WebsiteDao{
    @Override
    public int getCountByUrlHash(String urlHash) {
        String sql = "select count(*) from website where url_hash = ?";
        return this.getJdbcTemplate().queryForObject(sql, Integer.class, urlHash);
    }

    @Override
    public int createWebsite(Website website) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into website (title, summary, url, url_hash) values (?, ?, ?, ?)";
        this.getJdbcTemplate().update(con -> {
            PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            int i = 1;
            statement.setString(i++, website.getTitle());
            statement.setString(i++, website.getSummary());
            statement.setString(i++, website.getUrl());
            statement.setString(i++, website.getUrlHash());
            return statement;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public List<Website> getListOrderByRecent(int offset, int size) {
        String sql = "select * from website order by created_at desc limit ?, ?";
        return this.getJdbcTemplate().query(sql, new WebsiteRowMapper(), offset, size);
    }

    @Override
    public List<Website> getListOrderByCollectCount(int offset, int size) {
        return null;
    }

    @Override
    public List<Website> getListByAccount(Integer accountId, int offset, int size) {
        return null;
    }

    @Override
    public int getTotalCount() {
        String sql = "select count(*) from website";
        return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    }

    private class WebsiteRowMapper implements RowMapper<Website> {

        @Override
        public Website mapRow(ResultSet rs, int rowNum) throws SQLException {
            Website website = new Website();
            website.setId(rs.getInt("id"));
            website.setTitle(rs.getString("title"));
            website.setUrl(rs.getString("url"));
            website.setCreatedAt(rs.getTimestamp("created_at"));
            website.setUrlHash(rs.getString("url_hash"));
            website.setClickCount(rs.getInt("click_count"));
            website.setUpdatedAt(rs.getTimestamp("updated_at"));
            return website;
        }
    }
}
