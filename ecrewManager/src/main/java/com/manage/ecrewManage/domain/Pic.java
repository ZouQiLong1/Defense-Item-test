package com.manage.ecrewManage.domain;

public class Pic {
    private int id;
    private String pic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "Pic{" +
                "id=" + id +
                ", pic='" + pic + '\'' +
                '}';
    }
}
