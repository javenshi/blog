package com.centling.domain;

public class Blogs {
     private String    id;
    private String  blogsName;
    private String  blogsUrl;
    private int  blogsStatus;
    private int  blogsClick;
    private long  blogsDate	;
    private int blogsClassifyId	;
    private String  blogsClassifyName;
    private int userId;
    private int status;
    private String   userName;
    private long createdTime;
    private String    createdName;
    private int createdBy;
    private long lastModifiedTime;
    private String  lastModifiedName;
    private int  lastModifiedBy;
    private int deleteFlag;

    public Blogs() {
    }

    public void setBlogs(String uuid,User user) {
        long time= System.currentTimeMillis();
        this.id = uuid;
        this.blogsDate =time;
        this.createdTime =time;
        this.userId=user.getId();
        this.userName=user.getUserName();
        this.blogsUrl="";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogsName() {
        return blogsName;
    }

    public void setBlogsName(String blogsName) {
        this.blogsName = blogsName;
    }

    public String getBlogsUrl() {
        return blogsUrl;
    }

    public void setBlogsUrl(String blogsUrl) {
        this.blogsUrl = blogsUrl;
    }

    public int getBlogsStatus() {
        return blogsStatus;
    }

    public void setBlogsStatus(int blogsStatus) {
        this.blogsStatus = blogsStatus;
    }

    public int getBlogsClick() {
        return blogsClick;
    }

    public void setBlogsClick(int blogsClick) {
        this.blogsClick = blogsClick;
    }

    public long getBlogsDate() {
        return blogsDate;
    }

    public void setBlogsDate(long blogsDate) {
        this.blogsDate = blogsDate;
    }

    public int getBlogsClassifyId() {
        return blogsClassifyId;
    }

    public void setBlogsClassifyId(int blogsClassifyId) {
        this.blogsClassifyId = blogsClassifyId;
    }

    public String getBlogsClassifyName() {
        return blogsClassifyName;
    }

    public void setBlogsClassifyName(String blogsClassifyName) {
        this.blogsClassifyName = blogsClassifyName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public String getCreatedName() {
        return createdName;
    }

    public void setCreatedName(String createdName) {
        this.createdName = createdName;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public long getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(long lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public String getLastModifiedName() {
        return lastModifiedName;
    }

    public void setLastModifiedName(String lastModifiedName) {
        this.lastModifiedName = lastModifiedName;
    }

    public int getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(int lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public int getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(int deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
}
