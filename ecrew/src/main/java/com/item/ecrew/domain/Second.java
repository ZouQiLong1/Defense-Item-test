package com.item.ecrew.domain;

public class Second {
    private int id;
    private String name;
    private int sid;

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

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Override
    public String toString() {
        return "Second{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sid=" + sid +
                '}';
    }
}
