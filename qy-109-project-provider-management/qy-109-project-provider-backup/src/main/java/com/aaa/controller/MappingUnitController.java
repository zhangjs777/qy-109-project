package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingUnit;
import com.aaa.service.MappingUnitService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.CODE;
import static com.aaa.staticproperties.RedisProperties.DATA;
import static com.aaa.status.OperationStatus.*;
import static com.aaa.status.SelectStatus.SELECT_DATA_SUCCESS;
import static java.util.Map.*;

/**
 * @program: qy-109-project
 * @description:  单位表
 * @author: 张竞赛
 * @create: 2020-07-17 09:11
 **/
@RestController
public class MappingUnitController extends CommonController<MappingUnit> {

    @Autowired
    private MappingUnitService mappingUnitService;


    @Override
    public BaseService<MappingUnit> getBaseService() {
        return mappingUnitService;
    }


    /**
    * @Author: js.zhang
    * @Description: 查询黑白名单 0 黑 1白
    * @DateTime: 2020/7/22 11:39
    * @Params: [blankAndWirte]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/bwUnit")
    public ResultData bwUnit(@RequestParam("blank") String blank,@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){

        if (pageNo!=null&&pageSize!=null){
            PageHelper.startPage(pageNo,pageSize);
            List<MappingUnit> mappingUnitList = mappingUnitService.bwUnit(blank);
            PageInfo<MappingUnit> mappingUnitPageInfo = new PageInfo<MappingUnit>(mappingUnitList);
            if (mappingUnitPageInfo!=null&&mappingUnitPageInfo.getSize()>0){
                return  super.operationSuccess(mappingUnitPageInfo);
            }else {
                return  super.operationFailed();
            }
        }else {
            List<MappingUnit> mappingUnitList = mappingUnitService.bwUnit(blank);
            if (mappingUnitList!=null&&mappingUnitList.size()>0){
                return  super.operationSuccess(mappingUnitList);
            }else {
                return  super.operationFailed();
            }


        }




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
    @RequestMapping("/unitSelect")
    public ResultData unitSelect(@RequestBody MappingUnit mappingUnit){

        Map<String, Object> stringObjectMap = mappingUnitService.selectUnit(mappingUnit);
            if (SELECT_DATA_SUCCESS.getCode().equals(stringObjectMap.get(CODE))){
                return super.operationSuccess(stringObjectMap.get(DATA));
            }else {
                    return super.operationFailed();
            }


    }








    /**
    * @Author: js.zhang
    * @Description:  通过id查询单位具体信息
    * @DateTime: 2020/7/20 8:37
    * @Params: [mappingUnit]
    * @Return com.aaa.base.ResultData
    */
    @PostMapping("/selectUnitById")
    public ResultData selectUnitById(@RequestBody MappingUnit mappingUnit){
        if (mappingUnit!=null){
            MappingUnit mappingUnit1 = getBaseService().selectOne(mappingUnit);
            if (mappingUnit!=null){
                ResultData resultData = super.operationSuccess(mappingUnit1);
                return resultData;
            }
        }
            ResultData resultData = super.operationFailed();
            return resultData;

    }




    /**
    * @Author: js.zhang
    * @Description: 通过unitName 查询所有的 单位信息
    * @DateTime: 2020/7/17 9:20
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/selctMappingUnit")
    public  ResultData selectMappingUnit(@RequestBody MappingUnit mappingUnit){


        //调用查询方法
        PageInfo<MappingUnit> mappingUnitPageInfo = mappingUnitService.selectMappingUnit(mappingUnit);
        //判断返回值
        if (mappingUnitPageInfo!=null&&mappingUnitPageInfo.getSize()>0){
            return super.operationSuccess(mappingUnitPageInfo);
        } else{
            return super.operationFailed(DATA_NOT_EXIST.getMsg());
        }

    }




}
