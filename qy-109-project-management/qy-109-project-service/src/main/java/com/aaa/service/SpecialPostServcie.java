package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.SpecialPostMapper;
import com.aaa.model.Equipment;
import com.aaa.model.SpecialPost;
import com.aaa.utils.DataUtils;
import com.aaa.utils.IdUtils;
import com.aaa.utils.StringUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.aaa.status.OperationStatus.FAILED;
import static com.aaa.status.OperationStatus.SUCCESS;

/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/20 10:18
 * @description:
 **/

@Service
public class SpecialPostServcie extends BaseService<SpecialPost> {

    @Autowired
    private SpecialPostMapper specialPostMapper;


    public Boolean addSpecialPost(SpecialPost specialPost)throws Exception{

        //新增前判断传递过来的是否为空数据
        if (null != specialPost && !"".equals(specialPost)) {
            //为新增数据添加id
            specialPost.setId(IdUtils.getLongID());
            //为新增数据添加当前创建时间
            specialPost.setCreateTime(DataUtils.getNewDate());
            //新增操作，从父类通用service中获取新增方法
            Integer addResult = super.add(specialPost);
            if (0 < addResult) {
                //true 新增成功，
                return true;
            } else {
                //false 新增失败
                return false;
            }
        } else {
            //传递过来的user值为空，新增失败
            return false;
        }
    }


    /**
    * @Author:xfc
    * @Description:
     *    特殊岗位人员信息的更新操作
    * @Date: 2020/7/20 20:39
    * @param model:
    * @return: java.lang.Boolean
    *
    **/
    public Boolean updateSpecialPost(SpecialPost model) throws Exception {
        //先判断前台数据是否为空
        if (null != model && !"".equals(model)){
            //不为空，为其添加修改时间
            model.setModifyTime(DataUtils.getNewDate());
            //执行更新操作，从父类中获取更新的方法
            int updateResult = super.update(model);
            if (0 < updateResult){
                //true 成功
                return true;
            }
        }
        //前台传递过来为空，直接返回更新失败
        return false;
    }


    /**
    * @Author:xfc
    * @Description:
     *    根据主键删除一条数据
    * @Date: 2020/7/20 20:39
    * @param id:
    * @return: java.lang.Boolean
    *
    **/
    public Boolean deleteSpecialPost(Long id){
        //根据主键id删除
        int result = specialPostMapper.deleteByPrimaryKey(id);
        //判断是否删除成功
        if (0 < result){
            return true;
        } else {
            return false;
        }
    }
}
