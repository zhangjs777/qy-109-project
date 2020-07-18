package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.base.ResultData;
import com.aaa.mapper.MappingUnitMapper;
import com.aaa.model.MappingUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    * @Description: 测绘单位查询，单位名称模糊查询，单位地域和单位资质准确查询
    * @DateTime: 2020/7/17 15:05
    * @Params: [mappingUnit]
    * @Return java.util.List<com.aaa.model.MappingUnit>
    */
    public Map<String,Object> selectUnit(MappingUnit mappingUnit){

        Map<String, Object> resultMap=new HashMap<String, Object>();
        List<HashMap> hashMapList=new ArrayList<HashMap>();
        if (mappingUnit==null){
                        //没有条件查询全部id 和项目名
            hashMapList = mappingUnitMapper.selectUnitId();
            }else {
            hashMapList= mappingUnitMapper.selectUnit(mappingUnit);
            }

        if (hashMapList.size()>0){
            resultMap.put(CODE,SELECT_DATA_SUCCESS.getCode());
            resultMap.put(MSG,SELECT_DATA_SUCCESS.getMsg());
            resultMap.put(DATA,hashMapList);
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
    public  List<MappingUnit> selectMappingUnit(Integer pageNo, Integer pageSize, String orderByFiled, String orderWord,String unitName){

        //执行查询方法
        List<MappingUnit> mappingUnits = super.selectByFileds(pageNo, pageSize, null, orderByFiled, orderWord, unitName);

            if (mappingUnits!=null){
               return mappingUnits;
            }else {
                return null;
            }
    }




}
