package com.sv.honda.entity;

/**
 * 生成kendoGrid需要的实体类
 */
public class KendoGridEntiy {
    private int userPageSize;   //每页条数
    private int userHeight;   //高度
    private String url;  //地址
    private String windowTitle;  //编辑框名字
    private String sortable;  //是否支持排序
    private String filterable;  //是否支持过滤
    private Object object;  //传入的实体

    public int getUserPageSize() {
        return userPageSize;
    }

    public void setUserPageSize(int userPageSize) {
        this.userPageSize = userPageSize;
    }

    public int getUserHeight() {
        return userHeight;
    }

    public void setUserHeight(int userHeight) {
        this.userHeight = userHeight;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getWindowTitle() {
        return windowTitle;
    }

    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }

    public String getSortable() {
        return sortable;
    }

    public void setSortable(String sortable) {
        this.sortable = sortable;
    }

    public String getFilterable() {
        return filterable;
    }

    public void setFilterable(String filterable) {
        this.filterable = filterable;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

}
