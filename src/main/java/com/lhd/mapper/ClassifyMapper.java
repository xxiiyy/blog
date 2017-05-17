package com.lhd.mapper;


import com.lhd.entity.Classify;

import java.util.List;

/**
 * Created by lhd on 2017/5/14.
 * 分类
 */
public interface ClassifyMapper{

    /**
     * 查询全部的分类
     * @return
     */
    List<Classify> selectClassifyList();



}
