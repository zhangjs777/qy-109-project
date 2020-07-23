package com.aaa.service;

import com.aaa.model.Resource;
import com.aaa.properties.FtpProperties;
import com.aaa.utils.FileNameUtils;
import com.aaa.utils.FtpUtils;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

import static com.aaa.staticproperties.RedisProperties.*;
import static com.aaa.staticproperties.RedisProperties.POINT;
import static com.aaa.staticproperties.TimeForatProperties.*;
import static com.aaa.staticproperties.TimeForatProperties.DATE_FORMAT;

/**
 * @program: qy-109-project
 * @description:文件上传
 * @author: 张竞赛
 * @create: 2020-07-10 16:54
 **/
@Service
public class UploadService {
    @Autowired
    private FtpProperties ftpProperties;
    /**
    * @Author: js.zhang
    * @Description: 文件上传
    * @DateTime: 2020/7/10 17:16
    * @Params: [file]
    * @Return java.lang.Boolean
    */
    public Resource upload(MultipartFile file,Resource resource) {
        // 1.获取文件的远程名称(为了获取后缀名)
        String oleFileName = file.getOriginalFilename();
        // 2.生成新的文件名
        String newFileName = FileNameUtils.getFileName();
        // 3.截取后缀名，拼接到新的文件名上
        String substring = oleFileName.substring(oleFileName.lastIndexOf(POINT));
        newFileName = newFileName + substring;
        // 4.获取文件的上传路径(2020/07/10)
        // TODO 暂时没有完成，目前使用的是apache开源基金会的日期工具类，不符合咱们团队的技术水平，需要自己手动编写
        String filePath = DateUtil.formatDate(new Date(), DATE_FORMAT);
        // 5.调用文件上传工具类
        try {
            Boolean upload = FtpUtils.upload(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(),
                    ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, file.getInputStream());
        //判断是否上传成功
            if (upload){
                resource.setCreateTime(new Date());
                //后缀名加类型生成名字
                resource.setName(resource.getRefBizType()+substring);
                resource.setSize(file.getSize());
                resource.setPath(ftpProperties.getBasePath()+filePath+newFileName);
                resource.setType(file.getContentType()+"/"+substring);
                resource.setExtName(substring);
                return resource;
            }else{
                return null;
            }



        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
