package com.pinmost.web.dao.http;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by mei on 09/08/2017.
 */
@Repository("httpClientDao")
public class BasicHttpClientDao implements InitializingBean, DisposableBean, HttpClientDao {

    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/59.0.3071.115 Safari/537.36";
    private static final int TIME_OUT = 2000;
    private CloseableHttpClient httpClient;
    private int timeout = TIME_OUT;
    private String userAgent = USER_AGENT;
    private PoolingHttpClientConnectionManager connectionManager;
    private boolean connectionManagerShared = false;
    private List<Header> headerList = new ArrayList<>();

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public void setConnectionManager(PoolingHttpClientConnectionManager connectionManager) {
        this.connectionManager = connectionManager;
        connectionManagerShared = true;
    }

    public void setHeaderList(List<Header> headerList) {
        this.headerList = headerList;
    }

    protected CloseableHttpClient initHttpClient(){
        if (connectionManager == null) {
            connectionManager = new PoolingHttpClientConnectionManager();
            connectionManager.setDefaultMaxPerRoute(10);
            connectionManager.setMaxTotal(100);
        }

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(timeout)
                .setSocketTimeout(timeout)
                .setConnectionRequestTimeout(timeout)
                .build();

        CloseableHttpClient client = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setConnectionManagerShared(connectionManagerShared) //暂时采用false，一个Client对应一个ConnectionManager
                .disableCookieManagement()
                .setUserAgent(userAgent)
                .setDefaultRequestConfig(requestConfig)
                .setDefaultHeaders(headerList)
                .evictExpiredConnections()
                .evictIdleConnections(3, TimeUnit.MINUTES)
                .build();
        return client;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        httpClient = initHttpClient();
    }

    @Override
    public void destroy() throws Exception {
        HttpClientUtils.closeQuietly(httpClient);
        if (!connectionManagerShared) {
            connectionManager.close();
        }
    }

    @Override
    public String get(String url) throws IOException {
        HttpGet request = new HttpGet(url);
        return httpClient.execute(request, response -> {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            if (statusLine.getStatusCode() >= 300) {
                throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
            }
            if (entity == null) {
                throw new ClientProtocolException("http.error.response.no_content");
            }

            ContentType contentType = ContentType.get(entity);
            if (contentType == null || ! StringUtils.equalsIgnoreCase(contentType.getMimeType(), ContentType.TEXT_HTML.getMimeType())) {
                throw new ClientProtocolException("http.error.response.content_type_not_correct");
            }

            return EntityUtils.toString(entity);
        });
    }
}
