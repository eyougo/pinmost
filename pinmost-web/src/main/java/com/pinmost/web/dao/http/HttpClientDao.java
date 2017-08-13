package com.pinmost.web.dao.http;

import java.io.IOException;

/**
 * Created by mei on 09/08/2017.
 */
public interface HttpClientDao {
    String get(String url) throws IOException;
}
