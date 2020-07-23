package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.ResourceMapper;
import com.aaa.model.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-20 21:10
 **/
@Service
public class ResourceService extends BaseService<Resource> {
    @Autowired
    private ResourceMapper resourceMapper;

    public Boolean addResource(MultipartFile multipartFile,UploadService uploadService,Resource resource){
        Resource upload = uploadService.upload(multipartFile, resource);
        Integer add = super.add(upload);
        if (add>0){
            return true;
        }else{
            return false;
        }
    }

}
