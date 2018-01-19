package com.centling.domain;

public class User {
    private int id;
    private String uid;
    private String userName;
    private int uSource;
    private String location;
    private String description;
    private String profileUrl;
    private String gender;
    private String passWord;
    private String email;
    private int   integral	;
    private int   status;
    private long  createdTime	;
    private String createdName	;
    private int  createdBy;
    private long   lastModifiedTime	;
    private String  lastModifiedName;
    private int  lastModifiedBy;
    private int  deleteFlag;
    public User(){

    }
    public User(String uid, String userName, int uSource, String location, String profileUrl, String gender, String description) {
        this.uid = uid;
        this.userName = userName;
        this.uSource = uSource;
        this.location = location;
        this.profileUrl = profileUrl;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getuSource() {
        return uSource;
    }

    public void setuSource(int uSource) {
        this.uSource = uSource;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int userstatus) {
        this.status = userstatus;
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
