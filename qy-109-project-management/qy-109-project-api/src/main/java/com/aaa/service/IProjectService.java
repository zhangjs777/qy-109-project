package com.aaa.service;

import com.aaa.base.ResultData;
import com.aaa.model.LoginLog;
import com.aaa.model.MappingUnit;
import com.aaa.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        public  ResultData selectAllUser(@RequestBody HashMap hashMap);

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
        * @Description: 项目通过id对应审核表refId查询审核记录
        * @DateTime: 2020/7/17 11:04
        * @Params: [refId, pageNo, pageSize]
        * @Return com.aaa.base.ResultData
        */
    public ResultData selectByRefId(@RequestParam("refId") Long refId,
                                    @RequestParam("pageNo") Long pageNo,
                                    @RequestParam("pageSize") Long pageSize);
    /**
     * @Author: js.zhang
     * @Description: 测绘单位查询，单位名称模糊查询，单位地域和单位资质准确查询
     * @DateTime: 2020/7/17 15:30
     * @Params: [mappingUnit]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/unitSelect")
    public ResultData unitSelect(@RequestBody MappingUnit mappingUnit);

}
