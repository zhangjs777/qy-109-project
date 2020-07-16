package com.aaa.service;

import com.aaa.base.ResultData;
import com.aaa.model.Dictionary;
import com.aaa.model.LoginLog;
import com.aaa.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Company
 * @Author
 * @Date Create in 2020/7/15 9:30
 * @Description
 **/
@FeignClient(value = "")
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
    ResultData doLogin(@RequestBody User user);

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
    Integer addLoginLog(@RequestBody LoginLog loginLog);


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



}
