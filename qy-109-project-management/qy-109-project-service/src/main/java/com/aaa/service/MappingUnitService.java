package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.base.ResultData;
import com.aaa.mapper.MappingUnitMapper;
import com.aaa.model.MappingUnit;
import com.aaa.model.User;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.OrderStatic.*;
import static com.aaa.staticproperties.RedisProperties.*;
import static com.aaa.status.SelectStatus.*;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-17 09:06
 **/
@Service
public class MappingUnitService extends BaseService<MappingUnit> {
    @Autowired
    private MappingUnitMapper mappingUnitMapper;


        /**
        * @Author: js.zhang
        * @Description: 查询黑白名单  黑0 白1l
        * @DateTime: 2020/7/22 11:46
        * @Params: [blankAndWirte]
        * @Return java.util.List<com.aaa.model.MappingUnit>
        */
    public List<MappingUnit> bwUnit(String blankAndWirte){
        Example example = new Example(MappingUnit.class);
        Example.Criteria criteria = example.createCriteria();
        List<MappingUnit> mappingUnitList=null;
        if (blankAndWirte.equals(BLACK)){
            criteria.orLessThan("score",60);
          mappingUnitList= mappingUnitMapper.selectByExample(example);
        }else if (blankAndWirte.equals(WHITE)){
            criteria.orGreaterThan("score",100);
          mappingUnitList = mappingUnitMapper.selectByExample(example);
        }else {
            return null;
        }
        return mappingUnitList;


    }



    /**
    * @Author: js.zhang
    * @Description: 测绘单位查询，单位名称模糊查询，单位地域和单位资质准确查询
    * @DateTime: 2020/7/17 15:05
    * @Params: [mappingUnit]
    * @Return java.util.List<com.aaa.model.MappingUnit>
    */
    public Map<String,Object> selectUnit(MappingUnit mappingUnit){

        Map<String, Object> resultMap=new HashMap<String, Object>();

        List<MappingUnit> mappingUnitList = mappingUnitMapper.selectUnit(mappingUnit);

        if (mappingUnitList.size()>0){
            resultMap.put(CODE,SELECT_DATA_SUCCESS.getCode());
            resultMap.put(MSG,SELECT_DATA_SUCCESS.getMsg());
            resultMap.put(DATA,mappingUnitList);
        }else {
            resultMap.put(CODE,SELECT_DATA_FAILED.getCode());
            resultMap.put(MSG,SELECT_DATA_FAILED.getMsg());
        }
        return resultMap;


    }



    /**
    * @Author: js.zhang
    * @Description: 分页条件查询
    * @DateTime: 2020/7/17 14:41
    * @Params: [pageNo, pageSize, orderByFiled, orderWord, unitName]
    * @Return java.util.List<com.aaa.model.MappingUnit>
    */
    public   PageInfo<MappingUnit> selectMappingUnit(MappingUnit mappingUnit){
        //获取user全部属性
        Example example = new Example(MappingUnit.class);
        Example.Criteria criteria = example.createCriteria();
        PageHelper.startPage(mappingUnit.getPageNo(),mappingUnit.getPageSize());
        List<MappingUnit> mappingUnitList=null;

        if (mappingUnit.getUnitName()!=null){
            criteria.andLike("unitName","%"+mappingUnit.getUnitName()+"%");
            mappingUnitList = mappingUnitMapper.selectByExample(example);
        }else {
             mappingUnitList = mappingUnitMapper.selectAll();
        }
        PageInfo<MappingUnit> mappingUnitPageInfo = new PageInfo<>(mappingUnitList);
        return mappingUnitPageInfo;



    }



    public MappingUnit selectScore(Long id){
        MappingUnit selectScore = mappingUnitMapper.selectScore(id);
        if (selectScore!=null){
            return selectScore;
        }else {
            return null;
        }
    }



}
