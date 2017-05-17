package com.lhd.service.Impl;

import com.lhd.entity.Friend;
import com.lhd.mapper.FriendMapper;
import com.lhd.service.IFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by lhd on 2017/5/14.
 * 友链service
 */
@Service
public class FriendServiceImpl implements IFriendService{


    @Autowired
    private FriendMapper friendMapper;

    @Override
    public List<Friend> selectFriendList() {
        return friendMapper.selectFriendList();
    }
}
