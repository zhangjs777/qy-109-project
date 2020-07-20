package com.aaa.controller;

import cn.hutool.core.date.DateUtil;
import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Technicist;
import com.aaa.service.TechnicistService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.CODE;
import static com.aaa.status.OperationStatus.DATA_NOT_EXIST;
import static com.aaa.status.SelectStatus.SELECT_DATA_FAILED;
import static com.aaa.status.SelectStatus.SELECT_DATA_SUCCESS;

/**
 * @program: qy-109-project
 * @description:  技术人员表
 * @author: 张竞赛
 * @create: 2020-07-18 14:22
 **/
@RestController
public class TechnicistController extends CommonController<Technicist> {
    @Autowired
    private TechnicistService technicistService;
    @Override
    public BaseService<Technicist> getBaseService() {
        return technicistService;
    }


    /**
    * @Author: js.zhang
    * @Description: 通过id查询技术人员个人信息
    * @DateTime: 2020/7/18 15:19
    * @Params: [id]
    * @Return com.aaa.base.ResultData
    */
    @GetMapping("/selectTechById")
    public ResultData selectTechByUserId( Long id){

        //执行查询语句
        Map<String, Object> stringObjectMap = technicistService.selectTecById(id);

        if (SELECT_DATA_SUCCESS.getCode().equals(stringObjectMap.get(CODE))){
            return super.operationSuccess(stringObjectMap);
        }else if (SELECT_DATA_FAILED.getCode().equals(stringObjectMap.get(CODE))){
            return super.operationFailed();
        }else{
            return super.operationFailed(DATA_NOT_EXIST.getMsg());
        }
    }



    /**
    * @Author: js.zhang
    * @Description: 通过userId查询技术人员列表
    * @DateTime: 2020/7/18 14:34
    * @Params: [technicist, pageNo, pageSize]
    * @Return com.aaa.base.ResultData
    */
    @PostMapping("/selectTechByUid")
    public ResultData selectTechByUid( Technicist technicist,Integer pageNo,Integer pageSize){

        PageInfo<Technicist> technicistPageInfo = getBaseService().selectListByPage(technicist, pageNo, pageSize);
        if (technicistPageInfo!=null && technicistPageInfo.getSize()>0){
            return super.operationSuccess(technicistPageInfo);
        }else {
            return super.operationFailed();
        }


//        if (SELECT_DATA_SUCCESS.getCode().equals(stringObjectMap.get(CODE))){
//            return super.operationSuccess(stringObjectMap);
//        }else if (SELECT_DATA_FAILED.getCode().equals(stringObjectMap.get(CODE))){
//            return super.operationFailed();
//        }else{
//            return super.operationFailed(DATA_NOT_EXIST.getMsg());
//        }
    }

    /**
    * @Author: js.zhang
    * @Description: 修改技术人员信息
    * @DateTime: 2020/7/20 11:22
    * @Params: [technicist]
    * @Return com.aaa.base.ResultData
    */
    @PostMapping("/updateTechnicist")
    public ResultData updateTechnicist(@RequestBody Technicist technicist){
        //添加修改时间
        technicist.setModifyTime(new Date());
        Integer updateInteger = getBaseService().update(technicist);
        if (updateInteger>0){
            return super.operationSuccess();

        }else {
            return super.operationFailed();
        }
        
    }
    /**
    * @Author: js.zhang
    * @Description: 新增技术人员信息
    * @DateTime: 2020/7/20 11:24
    * @Params: [technicist]
    * @Return com.aaa.base.ResultData
    */
    @PostMapping("addTechnicist")
    public ResultData addTechnicist(@RequestBody Technicist technicist){
        //添加新增时间

        technicist.setCreateTime(new Date());
        Integer add = getBaseService().add(technicist);
        if (add>0){
            return super.operationSuccess();

        }else {
            return super.operationFailed();
        }
    }
    
    /**
    * @Author: js.zhang
    * @Description: 删除技术人员信息
    * @DateTime: 2020/7/20 11:45
    * @Params: [technicist]
    * @Return com.aaa.base.ResultData
    */
    public ResultData deleteTechnicist(@RequestBody Technicist technicist){
        Integer delete = getBaseService().delete(technicist);
        if (delete>0){
            return super.operationSuccess();

        }else {
            return super.operationFailed();
        }
    }
    

}
