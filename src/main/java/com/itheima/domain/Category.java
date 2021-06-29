package com.itheima.domain;

/**
 * @小兰少 Java开发小兰少
 * HAcker lanxiaopeng
 */
public class Category {

    private Integer cid;
    private String cname;



    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "CategoryMapper{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                '}';
    }
}
