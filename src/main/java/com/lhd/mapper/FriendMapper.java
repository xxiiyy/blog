package com.lhd.mapper;

import com.lhd.entity.Friend;
import com.lhd.entity.Tag;

import java.util.List;

/**
 * Created by lhd on 2017/5/14.
 * 友链
 */
public interface FriendMapper{


    /**
     * 获取所有友链
     * @return
     */
    List<Friend> selectFriendList();
}
