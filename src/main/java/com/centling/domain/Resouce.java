package com.centling.domain;

public class Resouce {
    private int id;
    private String resouceName;
    private String resouceUrl;
    private int resouceClick;
    private int userId;
    private long creatTime;
    private int status;
    private String userName;
    private String context;
    private long  deleteTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResouceName() {
        return resouceName;
    }

    public void setResouceName(String resouceName) {
        this.resouceName = resouceName;
    }

    public String getResouceUrl() {
        return resouceUrl;
    }

    public void setResouceUrl(String resouceUrl) {
        this.resouceUrl = resouceUrl;
    }

    public int getResouceClick() {
        return resouceClick;
    }

    public void setResouceClick(int resouceClick) {
        this.resouceClick = resouceClick;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public long getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(long deleteTime) {
        this.deleteTime = deleteTime;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
