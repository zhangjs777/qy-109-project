package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.base.ResultData;
import com.aaa.mapper.CheckPersonMapper;
import com.aaa.model.CheckPerson;
import com.aaa.vo.RoleVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * @program: qy-109-project
 * @author: 张梦波
 * @create: 2020-07-18 15:16
 **/
@Service
public class CheckPersonService extends BaseService<CheckPerson> {

    @Autowired
    CheckPersonMapper checkPersonMapper;

    /**
     * @Description: 查询抽查人员信息
     * @Param: []
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    public ResultData selectALLCheckPerson(){

        ResultData resultData=new ResultData();

        List<CheckPerson> checkPersonList=checkPersonMapper.selectAll();
        //判断数据是否为空
        if (null == checkPersonList || checkPersonList.size()<=0){
            //说明没有查到 查到为空
            resultData.setCode("30010");
            resultData.setMsg("查询不到数据");
        }else {
            //查到数据，并返回
            resultData.setCode("20010");
            resultData.setMsg("查询成功并且返回数据");
            resultData.setData(checkPersonList);
        }
        return resultData;
    }

    /**
     * @Description: 分页查询抽查人员信息
     * @Param: [roleVo]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    public ResultData selectALLCheckPersonByge(RoleVo roleVo){
        ResultData resultData=new ResultData();
        try {
            PageInfo<CheckPerson> checkPersonPageInfo=super.selectListByPage(roleVo.getCheckPerson(),roleVo.getPageNo(),roleVo.getPageSize());
            //判断分页数据是否为空
            if (null == checkPersonPageInfo || "".equals(checkPersonPageInfo)){
                //说明没有查到
                resultData.setCode("30010");
                resultData.setMsg("查询不到数据");
            }else{
                //查到数据并返回
                resultData.setCode("20010");
                resultData.setMsg("查询成功并且返回数据");
                resultData.setData(checkPersonPageInfo);
            }
        } catch (Exception e) {
            //有了异常以后，再次执行查询操作
            PageInfo<CheckPerson> checkPersonPageInfo=super.selectListByPage(roleVo.getCheckPerson(),roleVo.getPageNo(),roleVo.getPageSize());
            //判断分页数据是否为空
            if (null == checkPersonPageInfo || "".equals(checkPersonPageInfo)){
                //说明没有查到
                resultData.setCode("30010");
                resultData.setMsg("查询不到数据");
            }else{
                //查到数据并返回
                resultData.setCode("20010");
                resultData.setMsg("查询成功并且返回数据");
                resultData.setData(checkPersonPageInfo);
            }
        }
        return resultData;
    }

    /**
     * @Description:  抽查人员信息新增
     * @Param: [news]
     * @return: java.lang.Boolean
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    public Boolean addCheckPerson(CheckPerson checkPerson){
        //设置新增时间
        /*String createTime= DateUtil.now();*/
        checkPerson.setCreateTime(new Date());
        try {
            Integer add=super.add(checkPerson);
            if (add>0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    /**
     * @Description: 修改抽查人员信息
     * @Param: [news]
     * @return: java.lang.Boolean
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    public Boolean updateCheckPerson(CheckPerson checkPerson){
        //设置修改时间
        /*String createTime= DateUtil.now();*/
        checkPerson.setModifyTime(new Date());
        //判断是否为空
        if (null !=checkPerson && !"".equals(checkPerson)){
            try {
                Integer update=super.update(checkPerson);
                if (update>0){
                    return true;
                }else{
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        return null;
    }

    /**
     * @Description: 根据主键删除抽查人员信息
     * @Param: [id]
     * @return: java.lang.Boolean
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    public Boolean deleteCheckPerson(Long id){
        /*Long along=Long.valueOf(id);*/
        CheckPerson checkPerson=new CheckPerson();
        checkPerson.setId(id);
        int i= checkPersonMapper.deleteByPrimaryKey(id);
        if (i>0){
            return true;
        }else{
            return false;
        }
    }

}
