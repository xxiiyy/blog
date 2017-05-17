package com.lhd.service;


import com.lhd.entity.Tag;

import java.util.List;

/**
 * Created by lhd on 2017/5/14.
 * 标签
 */
public interface ITagService  {

    /**
     * 获取所有标签
     * @return
     */
    List<Tag> selectTagList();

}
