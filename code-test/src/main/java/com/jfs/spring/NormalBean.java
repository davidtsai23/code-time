package com.jfs.spring;

/**
 * @author caiweiwei
 * @date 2019-04-17
 */
public class NormalBean {
    private int id;
    private String desc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "NormalBean{" +
                "id=" + id +
                ", desc='" + desc + '\'' +
                '}';
    }
}
