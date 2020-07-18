package com.aaa.mapper;

import com.aaa.model.Audit;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;



public interface AuditMapper extends Mapper<Audit> {

    List<HashMap> selectByRefId(Long refId);
}