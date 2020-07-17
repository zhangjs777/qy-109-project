package com.aaa.controller;


import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.service.RoleService;
import com.aaa.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
* @Description:
* @Param:
* @return:
* @Author: ZMB
* @Date: 2020/7/16
*/
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
    * @Description:  查询所有的角色
    * @Param: []
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    @GetMapping("/allRoles")
    public ResultData selectAllRole(){
        ResultData resultData = roleService.selectAllRole();
        if ("20010" == resultData.getCode()){
            return super.operationSuccess(resultData.getData());
        }else {
            return super.operationFailed();
        }
    }

    /**
    * @Description:  简单的分页查询
    * @Param: [roleVo]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    @PostMapping("/pageRoles")
    public ResultData selectAllRoleByPage(@RequestBody RoleVo roleVo){
        ResultData resultData = roleService.selectAllRoleByPage(roleVo);
        if ("20010" == resultData.getCode()){
            return super.operationSuccess(resultData.getData());
        }else {
            return super.operationFailed();
        }
    }

    /**
    * @Description:  删除角色
    * @Param: [roleId]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/16
    */
    @PostMapping("/deleteRole")
    public ResultData deleteRole(@RequestParam("roleId") Long roleId){
        Boolean aBoolean = roleService.deleteRole(roleId);
        if (aBoolean == true){
            return super.operationSuccess();
        }else {
            return super.operationFailed();
        }
    }

    /**
    * @Description:  新增角色以及批量新增权限
    * @Param: [roleVo]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/16
    */
    @PostMapping("/insertRole")
    public ResultData insertRole(@RequestBody RoleVo roleVo){
        Boolean aBoolean = roleService.insertRole(roleVo);
        if (true == aBoolean){
            return super.operationSuccess();
        }else {
            return super.operationFailed();
        }
    }

    /**
    * @Description:  修改角色及其权限
    * @Param: [roleVo]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/16
    */
    @PostMapping("/updateRole")
    public ResultData updateRole(@RequestBody RoleVo roleVo){
        Boolean aBoolean = roleService.updateRole(roleVo);
        if (aBoolean==true){
            return super.operationSuccess();
        }else {
            return super.operationFailed();
        }
    }

}


