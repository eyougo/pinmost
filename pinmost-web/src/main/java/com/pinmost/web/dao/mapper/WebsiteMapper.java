package com.pinmost.web.dao.mapper;

import com.eyougo.common.dao.BaseMapper;
import com.pinmost.web.model.Website;
import com.pinmost.web.model.WebsiteAccount;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WebsiteMapper extends BaseMapper<Website> {

    int selectCountByUrlHash(String urlHash);

    List<WebsiteAccount> selectOrderByCreatedAt(@Param("offset") int offset, @Param("size") int size);

    List<Website> selectOrderByCollectCount(@Param("offset") int offset, @Param("size") int size);

    List<WebsiteAccount> selectOrderByClickCount(@Param("offset") int offset, @Param("size") int size);

    List<Website> selectByFromAccountId(@Param("accountId") Integer accountId, @Param("offset") int offset, @Param("size") int size);

    void updateClickCount(Integer id);
}