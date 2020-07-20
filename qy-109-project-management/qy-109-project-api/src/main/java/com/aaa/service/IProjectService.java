package com.aaa.service;

import com.aaa.base.ResultData;
import com.aaa.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Company
 * @Author
 * @Date Create in 2020/7/15 9:30
 * @Description
 **/
@FeignClient(value = "sys-interface")
public interface IProjectService {

    /**
     * @author
     * @description
     *      执行登录操作
     * @param [user]
     * @date 2020/7/15
     * @return com.aaa.lee.base.ResultData
     * @throws
    **/
    @PostMapping("/doLogin")
    public  ResultData doLogin(@RequestBody User user);

    /**
     * @author
     * @description
     *      新增日志
     * @param [loginLog]
     * @date 2020/7/15
     * @return java.lang.Integer
     * @throws
    **/
            @PostMapping("/addLoginLog")
    public Integer addLoginLog(@RequestBody LoginLog loginLog);
    /**
    * @Author: js.zhang
    * @Description: 查询全部user
    * @DateTime: 2020/7/16 15:14
    * @Params: [hashMap]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/selectAllUser")
    public ResultData selectAllUser(@RequestBody HashMap hashMap );

        @RequestMapping("/selectUser")
        public ResultData selectUser(@RequestBody Map map );

        @RequestMapping("/addUser")
        public ResultData addUser(@RequestBody Map map);

        @RequestMapping("/updateUser")
        public  ResultData updateUser(@RequestBody Map map );
        @RequestMapping("/updateUserStatus")
        public ResultData updateUserStatus(@RequestBody Map map,@RequestParam("ids[]") Integer [] ids);

    /**
    * @Author: js.zhang
    * @Description: 分页查询所有的mappingUnit 1.带条件  2不带条件
    * @DateTime: 2020/7/17 10:21
    * @Params: [hashMap]
    * @Return com.aaa.base.ResultData
    */
            @RequestMapping("/selctMappingUnit")
    public  ResultData selectMappingUnit(@RequestBody HashMap hashMap);
            @RequestMapping("selectAllMappingUnit")
    public  ResultData selectAllMappingUnit(@RequestBody Map map);

    /**
     * @Author: js.zhang
     * @Description: 通过refId查询对应审核信息
     * @DateTime: 2020/7/17 10:47
     * @Params: [refId]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/selectByRefId")
    public ResultData selectByRefId(@RequestBody HashMap map);



    /**
     * @Author: js.zhang
     * @Description: 测绘单位查询，单位名称模糊查询，单位地域和单位资质准确查询
     * @DateTime: 2020/7/17 15:30
     * @Params: [mappingUnit]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/unitSelect")
    public ResultData unitSelect(@RequestBody MappingUnit mappingUnit);



    /**
     * @Author:xfc
     * @Description:
     *          查询数据字典数据
     * @Date:2020/7/16
     * @param pageNo:
     * @param pageSize:
     * @return: com.aaa.base.ResultData
     *
     **/
    @PostMapping("/getAllDict")
    ResultData getAllDict(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author:xfc
     * @Description:
     *          添加字典数据
     * @Date:2020/7/16
     * @param dictionary:
     * @return: java.lang.Boolean
     *
     **/
    @PostMapping("/addDict")
    Boolean addDict(@RequestBody Dictionary dictionary);

    /**
     * @Author:xfc
     * @Description:
     *          删除字典数据
     * @Date:2020/7/16
     * @param dictionary:
     * @return: java.lang.Boolean
     *
     **/
    @PostMapping("/delDict")
    Boolean delDict(@RequestBody Dictionary dictionary);



    /**
     * @Author:xfc
     * @Description:
     *    查询所有测绘项目信息
     * @Date: 2020/7/17 19:24
     * @param mappingProject:
     * @return: com.aaa.base.ResultData
     *
     **/
    @PostMapping("/getAllMapProject")
    ResultData getAllMappingProject(@RequestBody MappingProject mappingProject);

    /**
     * @Author:xfc
     * @Description:
     *      通过名字查询项目信息
     * @Date: 2020/7/17 19:27
     * @param name:
     * @return: com.aaa.base.ResultData
     *
     **/
    @PostMapping("/getMapProByName")
    ResultData getMappingProjectByName(@RequestParam ("name") String name);


    /**
     * @Author:xfc
     * @Description:
     *          通过id 查询测绘信息
     * @Date: 2020/7/17 19:29
     * @param id:
     * @return: com.aaa.base.ResultData
     *
     **/
    @PostMapping("/getMapProById")
    ResultData getMappingProjectById(@RequestParam("id") String id);


    /**
     * @Author:xfc
     * @Description:
     *        查询未审核的项目登记信息
     * @Date: 2020/7/17 19:30
     * @param pageNo:
     * @param pageSize:
     * @return: com.aaa.base.ResultData
     *
     **/
    @PostMapping("/getMapProUnaudited")
    ResultData getMappingProjectUnaudited(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);



    /**
     * @Author:xfc
     * @Description:
     *        查询未审核的项目成功汇交信息
     * @Date: 2020/7/17 19:32
     * @param pageNo:
     * @param pageSize:
     * @return: com.aaa.base.ResultData
     *
     **/
    @PostMapping("/getMapProUnauditedInfo")
    ResultData getMappingProjectUnauditedInfo(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);



    /**
     * @Author:xfc
     * @Description:
     *     根据 name 查询未审核的项目登记信息
     * @Date: 2020/7/17 19:35
     * @param pageNo:
     * @param pageSize:
     * @param name:
     * @return: com.aaa.base.ResultData
     *
     **/
    @PostMapping("/getMapProUnauditedByName")
    ResultData getMappingProjectUnauditedByName(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize")Integer pageSize,String name);


    /**
     * @Author:xfc
     * @Description:
     *          根据 name 查询未审核的项目成果汇交信息
     * @Date: 2020/7/17 19:38
     * @param pageNo:
     * @param pageSize:
     * @param name:
     * @return: com.aaa.base.ResultData
     *
     **/
    @PostMapping("/getMapProUnaByNameInfo")
    ResultData  getMappingProjectUnauditedByNameInfo(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize")Integer pageSize,String name);


    /**
    * @Author:xfc
    * @Description:
     *      根据单位id 查找设备信息
    * @Date: 2020/7/20 8:52
    * @param equipment:
     * @param pageNo:
     * @param pageSize:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/getEquipmentByUnitId")
    ResultData getEquipmentByUnitId(@RequestBody Equipment equipment, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize")Integer pageSize);


    /**
    * @Author:xfc
    * @Description:
     *         查询全部设备信息
    * @Date: 2020/7/20 9:56
    * @param map:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/selectAllEquipment")
    ResultData selectAllEquipment(@RequestBody HashMap map);


    /**
    * @Author:xfc
    * @Description:
     *       根据设备id 查找设备信息
    * @Date: 2020/7/20 9:57
    * @param map:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/getEquipmentById")
    ResultData getEquipmentById(@RequestBody HashMap map);


    /**
    * @Author:xfc
    * @Description:
     *          添加设备信息
    * @Date: 2020/7/20 10:01
    * @param equipment:
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/addEquipment")
    Boolean addEquipment(@RequestBody Equipment equipment);

    /**
    * @Author:xfc
    * @Description:
     *       更新设备信息
    * @Date: 2020/7/20 10:01
    * @param equipment:
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/updateEquipment")
    Boolean updateEquipment(@RequestBody Equipment equipment);



    /**
    * @Author:xfc
    * @Description:
     *      根据id 删除设备信息
    * @Date: 2020/7/20 10:04
    * @param id:
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/deleteEquipment")
    Boolean deleteEquipment(@RequestParam("id") Long id);
    }
