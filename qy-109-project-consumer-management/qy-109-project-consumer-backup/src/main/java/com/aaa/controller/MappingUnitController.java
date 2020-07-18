package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-17 10:21
 **/
@RestController
public class MappingUnitController {
    @Autowired
    private IProjectService iProjectService;

    /**
    * @Author: js.zhang
    * @Description: 分页查询所有的待条件
    * @DateTime: 2020/7/17 10:23
    * @Params: [hashMap]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/selctMappingUnit")
    ResultData selectMappingUnit(@RequestBody HashMap hashMap){
        return iProjectService.selectMappingUnit(hashMap);
    }
    /**
    * @Author: js.zhang
    * @Description: 分页查询所有不带条件
    * @DateTime: 2020/7/17 10:23
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("selectAllMappingUnit")
    ResultData selectAllMappingUnit(@RequestBody Map map){
        return iProjectService.selectAllMappingUnit(map);

    }

}
