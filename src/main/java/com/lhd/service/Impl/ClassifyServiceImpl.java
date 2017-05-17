package com.lhd.service.Impl;

import com.lhd.entity.Classify;
import com.lhd.mapper.ClassifyMapper;
import com.lhd.service.IClassifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lhd on 2017/5/14.
 * 文章分类
 */
@Service
public class ClassifyServiceImpl implements IClassifyService {

    @Autowired
    private ClassifyMapper classifyMapper;

    @Override
    public List<Classify> selectClassifyList() {
        return classifyMapper.selectClassifyList();
    }
}
