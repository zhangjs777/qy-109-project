package com.aaa.mapper;

import com.aaa.model.Score;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;


public interface ScoreMapper extends Mapper<Score> {


    List<HashMap> selectByUnitId(HashMap hashMap);



}