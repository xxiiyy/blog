package com.lhd.service;


import com.lhd.entity.Friend;

import java.util.List;

/**
 * Created by lhd on 2017/5/14.
 * 分类
 */
public interface IFriendService {

    /**
     * 获取所有友链
     * @return
     */
    List<Friend> selectFriendList();

}
