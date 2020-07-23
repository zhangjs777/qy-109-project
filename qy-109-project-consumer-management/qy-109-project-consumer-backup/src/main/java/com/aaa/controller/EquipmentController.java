package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Equipment;
import com.aaa.redis.RedisService;
import com.aaa.service.EquipmentService;
import com.aaa.service.IProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.CODE;
import static com.aaa.status.OperationStatus.SUCCESS;

/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/18 9:58
 * @description:
 **/

@RestController
public class EquipmentController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /**
    * @Author:xfc
    * @Description:
     *        根据单位id 查询设备信息
    * @Date: 2020/7/18 14:59
    * @param :
     * @param :
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/getEquipmentByUnitId")
    public ResultData getEquipmentByUnitId(@RequestBody Equipment equipment, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        return iProjectService.getEquipmentByUnitId(equipment,pageNo,pageSize);
    }


    /**
    * @Author:xfc
    * @Description:
     *        查询全部设备信息
    * @Date: 2020/7/18 14:58
    * @param map:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/selectAllEquipment")
    public ResultData selectAllEquipment(@RequestBody HashMap map) {
       return iProjectService.selectAllEquipment(map);
    }

   /**
   * @Author:xfc
   * @Description:
    *       根据设备id 查询设备信息
   * @Date: 2020/7/18 14:34
   * @param map:
   * @return: com.aaa.base.ResultData
   *
   **/
    @PostMapping("/getEquipmentById")
    public ResultData getEquipmentById(@RequestBody HashMap map)  {
      return iProjectService.getEquipmentById(map);
    }

    /**
    * @Author:xfc
    * @Description:
     *          添加 设备信息
    * @Date: 2020/7/18 14:39
    * @param equipment:
     * @param :
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/addEquipment")
    public Boolean addEquipment(@RequestBody Equipment equipment){
      return iProjectService.addEquipment(equipment);
    }

    @PostMapping("/updateEquipment")
    public Boolean updateEquipment(@RequestBody Equipment equipment) {
        return iProjectService.updateEquipment(equipment);

    }


    @PostMapping("/deleteEquipment")
    public Boolean deleteEquipment(@RequestParam("id") Long id){
        return  iProjectService.deleteEquipment(id);
    }










}
