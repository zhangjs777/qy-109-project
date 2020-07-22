package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.EquipmentMapper;
import com.aaa.model.Equipment;
import com.aaa.redis.RedisService;
import com.aaa.utils.DataUtils;
import com.aaa.utils.IdUtils;
import com.aaa.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.status.OperationStatus.*;

/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/18 8:43
 * @description:
 *        仪器设备操作业务层
 **/

@Service
public class EquipmentService extends BaseService<Equipment> {
    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
    * @Author:xfc
    * @Description:
     *         查询全部设备信息
    * @Date: 2020/7/18 10:47
    * @param map:
    * @return: java.util.Map<java.lang.String,java.lang.Object>
    *
    **/
    public Map<String, Object> selectAllEquipment(HashMap map) throws Exception {
        HashMap<String, Object> resultMap = new HashMap<>();
        Equipment equipment = new Equipment();
        PageInfo<Equipment> equipmentPageInfo = super.selectListByPage(equipment, StringUtils.transToInt(map.get("pageNo")), StringUtils.transToInt(map.get("pageSize")));
        System.out.println(equipmentPageInfo.getSize());
        if (null != equipmentPageInfo && equipmentPageInfo.getSize()>0){
            resultMap.put("code",SUCCESS.getCode());
            resultMap.put("msg",SUCCESS.getMsg());
            resultMap.put("data",equipmentPageInfo);
            return resultMap;
        }
        resultMap.put("code",FAILED.getCode());
        resultMap.put("msg",FAILED.getMsg());
        resultMap.put("data",equipmentPageInfo);
        return resultMap;
    }





    /**
    * @Author:xfc
    * @Description:
     *          根据id查询仪器设备信息
    * @Date: 2020/7/18 8:49
    * @param map:
     * @param :
    * @return: com.github.pagehelper.PageInfo<com.aaa.model.Equipment>
    *
    **/
    public Map<String, Object> getEquipmentById(HashMap map){
        Equipment equipment = equipmentMapper.selectByPrimaryKey(map.get("id"));
        HashMap<String, Object> resultMap = new HashMap<>();
        if (equipment !=null){
             //有数据，返回数据
            resultMap.put("code",SUCCESS.getCode());
            resultMap.put("msg",SUCCESS.getMsg());
           resultMap.put("data", equipment);
           return resultMap;
        }
        resultMap.put("code",FAILED.getCode());
        return resultMap;
    }


    /**
    * @Author:xfc
    * @Description:
     *       根据单位id查询该单位下的仪器设备信息
    * @Date: 2020/7/18 11:08
    * @param map:
     * @param redisService:
    * @return: com.github.pagehelper.PageInfo<com.aaa.model.Equipment>
    *
    **/
    public PageInfo<Equipment> getEquipmentByUnitId(Map map){

        //先取出map中的pageNo， pageSize
        Integer pageNo = (Integer) map.get("pageNo");
        Integer pageSize = (Integer) map.get("pageSize");
        Long id = (Long) map.get("id");
        PageHelper.startPage(pageNo, pageSize);
        //查询

        System.out.println(id+"12212123312");

        List<Equipment> equipmentList = equipmentMapper.getEquipmentByUnitId(id);
        //判断
        if (0 < equipmentList.size()){
            return new PageInfo<Equipment>(equipmentList);

        } else {
            return null;
        }
    }


    /**
    * @Author:xfc
    * @Description:
     *       新增设备信息
    * @Date: 2020/7/18 9:28
    * @param equipment:
     * @param redisService:
    * @return: java.lang.Boolean
    *
    **/
    public Boolean addEquipment(Equipment equipment, RedisService redisService) throws Exception{
        //新增之前进行判断，判断传递的值是否为空数据
        if (null != equipment && !"".equals(equipment)){
            //为新增数据添加id
            equipment.setId(IdUtils.getLongID());
            //为新增数据添加当前创建时间
            equipment.setCreateTime(DataUtils.getNewDate());
            //进行新增操作，从父类通用service中获取新增方法
            Integer addResult=super.add(equipment);
            //对新增结果进行判断
            if (0<addResult){
                //添加成功
                return true;
            }else {
                //添加失败
                return false;
            }
        }else {
            //新增失败
            return false;
        }
    }


    /**
    * @Author:xfc
    * @Description:
     *      更新设备信息
    * @Date: 2020/7/18 9:41
    * @param equipment:
    * @return: java.lang.Boolean
    *
    **/
    public Boolean updateEquipment(Equipment equipment) throws Exception{
        //判断前台传来的数据是否为空
        if (null != equipment && !"".equals(equipment)){
            //不为空，为其添加修改时间
            equipment.setModifyTime(DataUtils.getNewDate());
            //进行更新，从父类获取更新方法
            Integer updateResult =super.update(equipment);
            if (0<updateResult){
                //更新成功
                return true;
            }
        }
        //前台传递过来为空，直接返回更新失败
        return false;
    }


    /**
    * @Author:xfc
    * @Description:
     *      根据主键id删除一条数据
    * @Date: 2020/7/18 9:46
    * @param id:
    * @return: java.lang.Boolean
    *
    **/
    public Boolean deleteEquipment(Long id){
        int Result = equipmentMapper.deleteByPrimaryKey(id);
        //判断是否删除成功
        if (0 < Result){
            //有数据，返回成功
            return true;
        } else {
            //无数据，返回失败
            return false;
        }

    }


}
