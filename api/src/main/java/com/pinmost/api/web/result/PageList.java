package com.pinmost.api.web.result;

import java.util.List;

/**
 * User: mei
 * Date: 2/17/16
 * Time: 18:31
 */
public class PageList<T> {

    private int nextStart;
    private int total;
    private List<T> list;

    public int getNextStart() {
        return nextStart;
    }

    public void setNextStart(int nextStart) {
        this.nextStart = nextStart;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
