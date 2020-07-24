package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.Dept;
import com.aaa.service.DeptService;
import com.aaa.service.IProjectService;
import com.aaa.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: qy-109-project
 * @author: 张梦波
 * @create: 2020-07-17 20:28
 **/
@RestController
/*@RequestMapping("/dept")*/
public class DeptController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /**
    * @Description:  部门模糊管理
    * @Param: [dept]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/20
    */
    @PostMapping("/selectDept")
    public ResultData selectDeptAll(Dept dept){
        return iProjectService.selectDeptAll(dept);
    }

    /**
    * @Description: 查询所有部门
    * @Param: []
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    @PostMapping("/allDepts")
    public ResultData selectAllDept(){
        return iProjectService.selectAllDept();
    }

    /**
    * @Description:  简单的分页查询
    * @Param: [roleVo]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    @PostMapping("/pageDepts")
    public ResultData selectAllDeptByPage(RoleVo roleVo){
        return iProjectService.selectAllDeptByPage(roleVo);
    }

    /**
    * @Description:  新增部门信息
    * @Param: [dept]
    * @return: com.aaa.base.ResultData<com.aaa.model.Dept>
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    @PostMapping("/insertDept")
    public ResultData<Dept> insertDept(Dept dept){
       return iProjectService.insertDept(dept);
    }

    /**
     * @Description:  修改部门信息
     * @Param: [dept]
     * @return: com.aaa.base.ResultData<com.aaa.model.Dept>
     * @Author: ZMB
     * @Date: 2020/7/17
     */
    @PostMapping("/updateDept")
    public ResultData<Dept> updateDept(Dept dept){
        return iProjectService.updateDept(dept);
    }

    /**
     * @Description:  删除部门信息
     * @Param: [dept]
     * @return: com.aaa.base.ResultData<com.aaa.model.Dept>
     * @Author: ZMB
     * @Date: 2020/7/17
     */
    @PostMapping("/deleteDept")
    public ResultData<Dept> deleteDept(Long deptId){
        return iProjectService.deleteDept(deptId);
    }


}
