package com.centling.domain;



import java.util.List;

public class Proposal {
    private int id;
    private int userId;
    private String userName;
    private String context;
    private long creatTime;
    private String blogId;
    private int toUserId;
    private int hasChild;
    private int pid;
    private String toUserName;
    private List<Proposal> children;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }


    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }

    public String getBlogId() {
        return blogId;
    }

    public void setBlogId(String blogId) {
        this.blogId = blogId;
    }

    public int getToUserId() {
        return toUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    public int getHasChild() {
        return hasChild;
    }

    public void setHasChild(int hasChild) {
        this.hasChild = hasChild;
    }

    public String getToUserName() {
        return toUserName;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public List<Proposal> getChildren() {
        return children;
    }

    public void setChildren(List<Proposal> children) {
        this.children = children;
    }
}
