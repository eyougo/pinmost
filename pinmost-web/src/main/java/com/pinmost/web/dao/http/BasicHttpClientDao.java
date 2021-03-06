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
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mei on 09/08/2017.
 */
@Repository("httpClientDao")
public class BasicHttpClientDao implements InitializingBean, DisposableBean, HttpClientDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicHttpClientDao.class);
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

            String content = EntityUtils.toString(entity, Charset.defaultCharset());
            try {
                if (contentType.getCharset() == null) {
                    String regEx="(?=<meta).*?(?<=charset)[\\s]*=[\\s]*['|\"]?([a-zA-Z0-9-]*)";
                    Pattern p=Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
                    Matcher m=p.matcher(content);  // 默认编码转成字符串，因为我们的匹配中无中文，所以串中可能的乱码对我们没有影响
                    boolean result=m.find();
                    if (result == true && m.groupCount() == 1) {
                        Charset contentCharset = Charset.forName(m.group(1));
                        if (!Charset.defaultCharset().equals(contentCharset)){
                            return new String(content.getBytes(Charset.defaultCharset()), contentCharset);
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
            return content;
        });
    }
}
