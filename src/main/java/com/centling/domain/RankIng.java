package com.centling.domain;

public class RankIng {
    private String  id;
    private String resouceName;
    private String resouceUrl;
    private int  resouceClick;
    private String  userName;
    private String  context	;
    private String   profileUrl;
    private int  status	;

    public RankIng() {
    }

    public RankIng(String id, String resouceName, String resouceUrl, int resouceClick, String userName, String context, String profileUrl) {
        this.id = id;
        this.resouceName = resouceName;
        this.resouceUrl = resouceUrl;
        this.resouceClick = resouceClick;
        this.userName = userName;
        this.context = context;
        this.profileUrl = profileUrl;
        this.status = 1;
    }

    public RankIng(String id,String resouceName) {
        this.id = id;
        this.resouceName = resouceName;
        this.status = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
