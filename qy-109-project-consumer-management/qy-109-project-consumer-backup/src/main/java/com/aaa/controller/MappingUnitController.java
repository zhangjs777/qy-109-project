package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.MappingUnit;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @Description: 查询黑白名单 0 黑 1白
     * @DateTime: 2020/7/22 11:39
     * @Params: [blankAndWirte]
     * @Return com.aaa.base.ResultData
     */
    @GetMapping("/bwUnit")
    public ResultData bwUnit(@RequestParam("blankAndWirte") String blankAndWirte,@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
       return iProjectService.bwUnit(blankAndWirte,pageNo,pageSize);
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
      return   iProjectService.updateUnit(map);
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
    return  iProjectService.unitSelect(mappingUnit);
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
       return iProjectService.updateMappingUnit(map);
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
        return  iProjectService.selectAllMappingUnit(map);
    }

    /**
     * @Author: js.zhang
     * @Description:  通过id查询单位具体信息
     * @DateTime: 2020/7/20 8:37
     * @Params: [mappingUnit]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/selectUnitById")
    public ResultData selectUnitById(@RequestBody  MappingUnit mappingUnit){
        return  iProjectService.selectUnitById(mappingUnit);
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
        return  iProjectService.selectUnitById(mappingUnit);
    }

}
