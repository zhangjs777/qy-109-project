package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingUnit;
import com.aaa.model.Resource;
import com.aaa.service.ResourceService;
import com.aaa.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @program: qy-109-project
 * @description:附件表
 * @author: 张竞赛
 * @create: 2020-07-20 21:08
 **/
@RestController
public class ResourceController extends CommonController<Resource> {
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UploadService uploadService;
    @Override
    public BaseService<Resource> getBaseService() {
        return resourceService;
    }
    /**
    * @Author: js.zhang
    * @Description: 通过refBizId
    * @DateTime: 2020/7/20 21:25
    * @Params: [resource]
    * @Return com.aaa.base.ResultData
    */
    @PostMapping("/selectByRefBizId")
    public ResultData selectByRefBizId(Resource resource){
        List<Resource> resources = getBaseService().selectList(resource);
        if (resource!=null&&resource.getSize()>0){
          return   super.operationFailed(resource);
        }
        return super.operationFailed();
    }
    /**
    * @Author: js.zhang
    * @Description: 文件上传
    * @DateTime: 2020/7/21 16:37
    * @Params: [multipartFile, resource]
    * @Return com.aaa.base.ResultData
    */
    @RequestMapping("/addResouce")
    public ResultData addResource(MultipartFile multipartFile, Resource resource){
        Boolean aBoolean = resourceService.addResource(multipartFile, uploadService, resource);
        if (aBoolean){
           return super.operationSuccess();
        }
        return  super.operationFailed();


    }

}
