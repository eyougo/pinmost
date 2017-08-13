package com.pinmost.web.model;

import com.eyougo.common.model.BaseModel;

import java.io.Serializable;

public class Website extends BaseModel implements Serializable{
    private static final long serialVersionUID = 2910355695910552721L;

    private Integer id;

    private String title;

    private String summary;

    private String url;

    private Integer clickCount;

    private String urlHash;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getClickCount() {
        return clickCount;
    }

    public void setClickCount(Integer clickCount) {
        this.clickCount = clickCount;
    }

    public String getUrlHash() {
        return urlHash;
    }

    public void setUrlHash(String urlHash) {
        this.urlHash = urlHash == null ? null : urlHash.trim();
    }
}