package com.centling.domain;

public class Notice {
    private int id;
    private String name;
    private long creatTime;
    private String context;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(long creatTime) {
        this.creatTime = creatTime;
    }



    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }


}
