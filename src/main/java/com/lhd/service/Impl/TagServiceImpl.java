package com.lhd.service.Impl;

import com.lhd.entity.Tag;
import com.lhd.mapper.TagMapper;
import com.lhd.service.ITagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by lhd on 2017/5/14.
 * 分类
 */
@Service
public class TagServiceImpl  implements ITagService{

    @Autowired
    private TagMapper tagMapper;

    @Override
    public List<Tag> selectTagList() {
        return tagMapper.selectTagList();
    }
}
