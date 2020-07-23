package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.AuditMapper;
import com.aaa.model.Audit;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


import java.util.List;





/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-17 10:25
 **/
@Service
public class AuditService extends BaseService<Audit> {

    @Autowired
    private AuditMapper auditMapper;


    /**
    * @Author: js.zhang
    * @Description: 通过refid查询审核记录
    * @DateTime: 2020/7/17 10:42
    * @Params: [refId]
    * @Return com.aaa.model.Audit
    */
    public PageInfo<Audit> selectByRefId(Audit audit){
        PageHelper.startPage(audit.getPageNo(),audit.getPageSize());
        //获取audit全部属性
        Example example = new Example(Audit.class);
        Example.Criteria criteria = example.createCriteria();
        List<Audit> auditList=null;
        if (audit.getRefId()!=null||audit.getName()!=null){
            //拼接条件模糊查询auditname和精确查询deptid
            criteria.andEqualTo("refId",audit.getRefId()).andEqualTo("name",audit.getName());
            auditList = auditMapper.selectByExample(example);
        }else {
            auditList = auditMapper.selectAll();
        }
        PageInfo<Audit> auditPageInfo = new PageInfo<Audit>(auditList);
        return  auditPageInfo;

    }

}
