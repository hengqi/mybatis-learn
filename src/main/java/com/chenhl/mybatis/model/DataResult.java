package com.chenhl.mybatis.model;

import java.util.List;

public class DataResult<T> {

    private List<T> data;
    private int count;

    public DataResult() {
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
