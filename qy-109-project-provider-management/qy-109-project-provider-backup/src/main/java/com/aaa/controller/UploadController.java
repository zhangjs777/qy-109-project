package com.aaa.controller;

import com.aaa.model.Resource;
import com.aaa.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/21 15:19
 * @description:
 **/

@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @PostMapping(value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Resource uploadFile(@RequestBody MultipartFile file){
        return uploadService.uploadFile(file);
    }



}
