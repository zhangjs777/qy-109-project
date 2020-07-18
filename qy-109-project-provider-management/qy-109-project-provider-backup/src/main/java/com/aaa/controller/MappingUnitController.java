package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingUnit;
import com.aaa.service.MappingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.CODE;
import static com.aaa.staticproperties.RedisProperties.DATA;
import static com.aaa.status.OperationStatus.*;
import static java.util.Map.*;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-17 09:11
 **/
@RestController
@RequestMapping("/mappingUnit")
public class MappingUnitController extends CommonController<MappingUnit> {

    @Autowired
    private MappingUnitService mappingUnitService;


    @Override
    public BaseService<MappingUnit> getBaseService() {
        return mappingUnitService;
    }



    /**
    * @Author: js.zhang
    * @Description: 修改mappingUnit
    * @DateTime: 2020/7/17 15:50
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @PostMapping("updateUnit")
    public ResultData updateUnit(@RequestBody Map map){
        ResultData resultData = super.update(map);
        return resultData;
    }

    /**
    * @Author: js.zhang
    * @Description: 测绘单位查询，单位名称模糊查询，单位地域和单位资质准确查询
    * @DateTime: 2020/7/17 15:36
    * @Params: [mappingUnit]
    * @Return com.aaa.base.ResultData
    */
    @PostMapping("/unitSelect")
    public ResultData unitSelect(@RequestBody MappingUnit mappingUnit){

        Map<String, Object> stringObjectMap = mappingUnitService.selectUnit(mappingUnit);
            if (SElECT_SUCCESS.getCode().equals(stringObjectMap.get(CODE))){
                return super.operationSuccess(stringObjectMap.get(DATA));
            }else {
                    return super.operationFailed();
            }


    }


    /**
    * @Author: js.zhang
    * @Description: 修改单位信息
    * @DateTime: 2020/7/17 11:45
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("updateAllMappingUnit")
    public ResultData updateMappingUnit(@RequestBody Map map){
        ResultData resultData = super.update(map);
        return resultData;
    }





    /**
    * @Author: js.zhang
    * @Description: 分页查询所有数据 mappingUnit
    * @DateTime: 2020/7/17 10:16
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("selectAllMappingUnit")
    public  ResultData selectAllMappingUnit(@RequestBody Map map){
        ResultData resultData = super.selectAllByPage(map);
        return resultData;
        
    }

    /**
    * @Author: js.zhang
    * @Description: 待条件的 查询所有的 单位信息
    * @DateTime: 2020/7/17 9:20
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/selctMappingUnit")
    public  ResultData selectMappingUnit(@RequestBody HashMap hashMap){
        //转换数据
        Integer pageNo = (Integer) hashMap.get("pageNo");
        Integer pageSize = (Integer) hashMap.get("pageSize");
        String orderByFiled = hashMap.get("orderByFiled").toString();
        String orderWord = hashMap.get("orderWord").toString();
        String unitName = hashMap.get("unitName").toString();


        //调用查询方法
        List<MappingUnit> mappingUnits = mappingUnitService.selectMappingUnit(pageNo, pageSize, orderByFiled, orderWord, unitName);
        //判断返回值
        if (mappingUnits!=null&&mappingUnits.size()>0){
            return super.operationSuccess(mappingUnits);
        } else{
            return super.operationFailed(DATA_NOT_EXIST.getMsg());
        }

    }


}
