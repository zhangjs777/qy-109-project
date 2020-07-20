package com.aaa.mapper;

import com.aaa.model.Equipment;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface EquipmentMapper extends Mapper<Equipment> {

    /**
    * @Author:xfc
    * @Description:
     *            根据单位id查询该单位下的仪器设备信息
    * @Date: 2020/7/18 8:39
    * @param id:
    * @return: java.util.List<com.aaa.model.Equipment>
    *
    **/

    List<Equipment> getEquipmentByUnitId(Long id);



}