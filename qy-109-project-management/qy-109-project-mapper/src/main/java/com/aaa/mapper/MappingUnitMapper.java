package com.aaa.mapper;


import com.aaa.model.MappingUnit;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: 测绘单位查询-通用mapper
 * @Param:
 * @return:
 * @Author:
 * @Date: 2020/7/16
 */
public interface MappingUnitMapper extends Mapper<MappingUnit> {

  /**
  * @Author: js.zhang
  * @Description: 测绘单位查询，单位名称模糊查询，单位地域和单位资质准确查询
  * @DateTime: 2020/7/17 14:33
  * @Params: [mappingUnit]
  * @Return java.util.List<java.util.HashMap>
  */
  public List<MappingUnit> selectUnit(MappingUnit mappingUnit);

  /**
  * @Author: js.zhang
  * @Description: 查询所有的测绘单位
  * @DateTime: 2020/7/17 14:49
  * @Params: []
  * @Return java.util.List<java.util.HashMap>
  */
  public List<HashMap> selectUnitId();

  MappingUnit selectScore(Long id);


}