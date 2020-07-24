package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.User;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-16 09:34
 **/
@RestController
public class UserController extends BaseController {
    @Autowired
    private IProjectService iProjectService;

    /**
    * @Author: js.zhang
    * @Description: 新增用户
    * @DateTime: 2020/7/23 17:29
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/addUser")
    ResultData addUser(      Map map){
       return iProjectService.addUser(map);
    }

    /**
    * @Author: js.zhang
    * @Description: 修改用户
    * @DateTime: 2020/7/23 17:30
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/updateUser")
    ResultData updateUser(      Map map ){
       return iProjectService.updateUser(map);
    }


    /**
    * @Author: js.zhang
    * @Description: 批量删除
    * @DateTime: 2020/7/23 17:31
    * @Params: [ids]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/deleteUserById")
    ResultData updateUserStatus( Integer [] ids){
       return iProjectService.updateUserStatus(ids);
    }



    /**
    * @Author: js.zhang
    * @Description: 分页 条件 查询
    * @DateTime: 2020/7/23 17:35
    * @Params: [user]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/selectAllUser")
    public ResultData selectAllUser(      User user ){
      return   iProjectService.selectAllUser(user);
    }


    /**
     * @Author: js.zhang
     * @Description: 通过id查询user具体信息 部门 管理员
     * @DateTime: 2020/7/22 21:35
     * @Params: [id]
     * @Return com.aaa.base.ResultData
     */
    @RequestMapping("/selectUserById")
    public ResultData selectUserById( Long id){
     return    iProjectService.selectUserById(id);
    }




}
