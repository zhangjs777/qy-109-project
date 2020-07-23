package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.Equipment;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


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
    public ResultData getEquipmentByUnitId( Equipment equipment, Integer pageNo, Integer pageSize){
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
    public ResultData selectAllEquipment( HashMap map) {
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
    public ResultData getEquipmentById( HashMap map)  {
      return iProjectService.getEquipmentById(map);
    }

    /**
    * @Author:xfc
    * @Description:
     *          添加设备信息
    * @Date: 2020/7/18 14:39
    * @param equipment:
     * @param :
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/addEquipment")
    public Boolean addEquipment( Equipment equipment){
      return iProjectService.addEquipment(equipment);
    }

    @PostMapping("/updateEquipment")
    public Boolean updateEquipment(Equipment equipment) {
        return iProjectService.updateEquipment(equipment);

    }


    @PostMapping("/deleteEquipment")
    public Boolean deleteEquipment( Long id){
        return  iProjectService.deleteEquipment(id);
    }










}
