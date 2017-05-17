package com.lhd.entity;


/**
 * Created by lhd on 2017/5/17.
 * 朋友表
 */
public class Friend {

    /**
     * id
     */
    private String id;

    /**
     * 朋友称呼
     */
    private String friend;

    /**
     * 标语
     */
    private String slogan;

    private String link;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFriend() {
        return friend;
    }

    public void setFriend(String friend) {
        this.friend = friend;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Friend{" +
                "id='" + id + '\'' +
                ", friend='" + friend + '\'' +
                ", slogan='" + slogan + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
