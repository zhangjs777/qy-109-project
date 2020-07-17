package com.aaa.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.base.BaseService;
import com.aaa.base.ResultData;
import com.aaa.mapper.DeptMapper;
import com.aaa.model.Dept;
import com.aaa.vo.RoleVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: qy-109-project
 * @author: 张梦波
 * @create: 2020-07-17 15:56
 **/
@Service
public class DeptService extends BaseService<Dept> {

    @Autowired
    DeptMapper deptMapper;

    /**
    * @Description: 查询所有部门 dept
     * role的
    * @Param: []
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    public ResultData selectAllDept(){
        ResultData resultData=new ResultData();
        List<Dept> depts=deptMapper.selectAll();
        if (null == depts || depts.size()<=0){
            //说明没有查到 查到为空
            resultData.setCode("30010");
            resultData.setMsg("查询不到数据");
        }else {
            resultData.setCode("20010");
            resultData.setMsg("查询成功并且返回数据");
            resultData.setData(depts);
        }
        return resultData;
    }

    /**
    * @Description:  分页查询
    * @Param: [roleVo]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    public ResultData selectAllDeptByge(RoleVo roleVo){
        ResultData resultData=new ResultData();
        try {
            PageInfo<Dept> deptPageInfo=super.selectListByPage(roleVo.getDept(),roleVo.getPageNo(),roleVo.getPageSize());
            if (null== deptPageInfo||"".equals(deptPageInfo)){
                //说明没有查到
                resultData.setCode("30010");
                resultData.setMsg("查询不到数据");
            }else{
                resultData.setCode("20010");
                resultData.setMsg("查询成功并且返回数据");
                resultData.setData(deptPageInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }


    /**
    * @Description: 部门新增
    * @Param: [dept]
    * @return: java.lang.Boolean
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    public Boolean addDept(Dept dept){
        String createTime = DateUtil.now();
        dept.setCreateTime(createTime);
        try{
            Integer add = super.add(dept);
            if (add > 0){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    /**
    * @Description: 根据主键修改部门
    * @Param: [dept]
    * @return: java.lang.Boolean
    * @Author: ZMB
    * @Date: 2020/7/17
    */
    public Boolean updateDept(Dept dept){
        String time = DateUtil.now();
        dept.setModifyTime(time);
        try {
            Integer update = super.update(dept);
            if (update>0){
                return true;
            }else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /** 
    * @Description:  通过主键id删除菜单或者按钮
    * @Param: [deptId] 
    * @return: java.lang.Boolean 
    * @Author: ZMB 
    * @Date: 2020/7/17 
    */
    public Boolean deleteDept(Long deptId){
        int i = deptMapper.deleteByPrimaryKey(deptId);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }




}
