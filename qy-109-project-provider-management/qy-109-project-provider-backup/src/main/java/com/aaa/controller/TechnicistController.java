package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Technicist;
import com.aaa.service.TechnicistService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
