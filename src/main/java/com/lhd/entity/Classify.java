package com.lhd.entity;

/**
 * Created by lhd on 2017/5/14.
 * 标签信息
 */

public class Classify {

    /**
     * 标签id
     */
    private String id;

    /**
     * 文章标签名
     */
    private String classify;

    /**
     * 这个分类包含文章
     */
    private Integer num;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "id='" + id + '\'' +
                ", classify='" + classify + '\'' +
                '}';
    }

}
