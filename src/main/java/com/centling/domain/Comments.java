package com.centling.domain;

public class Comments {
    private int id;
    private String AnnouncementContext;
    private long creatTime;
    private int userId;
    private String UserName;
    private String blogId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnnouncementContext() {
        return AnnouncementContext;
    }

    public void setAnnouncementContext(String announcementContext) {
        AnnouncementContext = announcementContext;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }
}
