package com.aaa.controller;


import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.Role;
import com.aaa.service.IProjectService;
import com.aaa.service.RoleService;
import com.aaa.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @Description:
* @Param:
* @return:
* @Author: ZMB
* @Date: 2020/7/16
*/
@RestController
/*@RequestMapping("/role")*/
public class RoleController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /** 
    * @Description: 模糊查询角色 
    * @Param: [role] 
    * @return: com.aaa.base.ResultData 
    * @Author: ZMB 
    * @Date: 2020/7/20 
    */
    @PostMapping("/selectRole")
    public ResultData selectRoleAll(@RequestBody Role role){
        return iProjectService.selectRoleAll(role);
    }
    
    /**
    * @Description:  查询所有的角色
    * @Param: []
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    @PostMapping("/allRoles")
    public ResultData selectallrole(){
        return iProjectService.selectallrole();
    }

    /**
    * @Description:  简单的分页查询
    * @Param: [roleVo]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    @PostMapping("/pageRoles")
    public ResultData selectAllRoleByPage(RoleVo roleVo){
        return iProjectService.selectAllRoleByPage(roleVo);
    }

    /**
    * @Description:  删除角色
    * @Param: [roleId]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/16
    */
    @PostMapping("/deleteRole")
    public ResultData deleteRole(Long roleId){
        return iProjectService.deleteRole(roleId);
    }

    /**
    * @Description:  新增角色以及批量新增权限
    * @Param: [roleVo]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/16
    */
    @PostMapping("/insertRole")
    public ResultData insertRole(RoleVo roleVo){
        return iProjectService.insertRole(roleVo);
    }

    /**
    * @Description:  修改角色及其权限
    * @Param: [roleVo]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/16
    */
    @PostMapping("/updateRole")
    public ResultData updateRole(RoleVo roleVo){
        return iProjectService.updateRole(roleVo);
    }

}


