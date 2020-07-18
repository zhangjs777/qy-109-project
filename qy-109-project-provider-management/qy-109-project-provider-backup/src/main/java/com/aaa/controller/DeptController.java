package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.Dept;
import com.aaa.service.DeptService;
import com.aaa.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: qy-109-project
 * @author: 张梦波
 * @create: 2020-07-17 20:28
 **/
@RestController
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
    private DeptService deptService;

    /**
    * @Description: 查询所有部门
    * @Param: []
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    @PostMapping("/allDepts")
    public ResultData selectAllRole(){
        ResultData resultData = deptService.selectAllDept();
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
    @PostMapping("/pageDepts")
    public ResultData selectAllDeptByPage(@RequestBody RoleVo roleVo){
        ResultData resultData = deptService.selectAllDeptByge(roleVo);
        if ("20010" == resultData.getCode()){
            return super.operationSuccess(resultData.getData());
        }else {
            return super.operationFailed();
        }
    }

    /**
    * @Description:  新增部门信息
    * @Param: [dept]
    * @return: com.aaa.base.ResultData<com.aaa.model.Dept>
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    @PostMapping("/insertDept")
    public ResultData<Dept> insertDept(@RequestBody Dept dept){
        Boolean aBoolean = deptService.addDept(dept);
        if (true == aBoolean){
            return super.operationSuccess();
        }else {
            return super.operationFailed();
        }
    }

    /**
     * @Description:  修改部门信息
     * @Param: [dept]
     * @return: com.aaa.base.ResultData<com.aaa.model.Dept>
     * @Author: ZMB
     * @Date: 2020/7/17
     */
    @PostMapping("/updateDept")
    public ResultData<Dept> updateDept(@RequestBody Dept dept){
        Boolean aBoolean = deptService.updateDept(dept);
        if (true == aBoolean){
            return super.operationSuccess();
        }else {
            return super.operationFailed();
        }
    }

    /**
     * @Description:  删除部门信息
     * @Param: [dept]
     * @return: com.aaa.base.ResultData<com.aaa.model.Dept>
     * @Author: ZMB
     * @Date: 2020/7/17
     */
    @PostMapping("/deleteDept")
    public ResultData<Dept> deleteDept(@RequestParam("deptId") Long deptId){
        Boolean aBoolean = deptService.deleteDept(deptId);
        if (true == aBoolean){
            return super.operationSuccess();
        }else {
            return super.operationFailed();
        }
    }


}
