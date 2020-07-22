package com.aaa.service;

import com.aaa.mapper.ResourceMapper;
import com.aaa.model.Resource;
import com.aaa.properties.FtpProperties;
import com.aaa.utils.DataUtils;
import com.aaa.utils.FileNameUtils;
import com.aaa.utils.FtpUtils;
import com.aaa.utils.IdUtils;
import org.apache.commons.httpclient.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Date;
import static com.aaa.staticproperties.RedisProperties.POINT;
import static com.aaa.staticproperties.TimeForatProperties.*;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-10 16:54
 **/
@Service
public class UploadService {
    @Autowired
    private FtpProperties ftpProperties;

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * @Author: js.zhang
     * @Description: 文件上传
     * @DateTime: 2020/7/10 17:16
     * @Params: [file]
     * @Return java.lang.Boolean
     */
    public Boolean upload(MultipartFile file) {
        // 1.获取文件的远程名称(为了获取后缀名)
        String oleFileName = file.getOriginalFilename();
        // 2.生成新的文件名
        String newFileName = FileNameUtils.getFileName();
        // 3.截取后缀名，拼接到新的文件名上
        newFileName = newFileName + oleFileName.substring(oleFileName.lastIndexOf(POINT));
        // 4.获取文件的上传路径(2020/07/10)
        // TODO 暂时没有完成，目前使用的是apache开源基金会的日期工具类，不符合咱们团队的技术水平，需要自己手动编写
        String filePath = DateUtil.formatDate(new Date(), DATE_FORMAT);
        // 5.调用文件上传工具类
        try {
            return FtpUtils.upload(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(),
                    ftpProperties.getPassword(), ftpProperties.getBasePath(), filePath, newFileName, file.getInputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }



    public Resource uploadFile(MultipartFile file) {
        Resource resource = null;
        // 1.获取文件的远程名称(为了获取后缀名)
        String oldFileName = file.getOriginalFilename();
        // 2.生成新的文件名
        String newFileName = FileNameUtils.getFileName();
        // 3.截取后缀名，拼接到新的文件名上
        newFileName = newFileName + oldFileName.substring(oldFileName.lastIndexOf(POINT));
        // 4.获取文件的上传路径(2020/07/10)
        // TODO 暂时没有完成，目前使用的是apache开源基金会的日期工具类，不符合咱们团队的技术水平，需要自己手动编写
        String filePath = DateUtil.formatDate(new Date(), DATE_FORMAT);
        // 5.调用文件上传工具类
        try {
            Boolean result = FtpUtils.upload(ftpProperties.getHost(), ftpProperties.getPort(), ftpProperties.getUsername(),
                    ftpProperties.getPassword(), filePath, ftpProperties.getBasePath(), newFileName, file.getInputStream());
            if (result) {
                //说明文件上传成功，需要把文件的路径更新到进数据库中
                resource.setId(Long.valueOf(IdUtils.getUUID()));
                resource.setName(oldFileName);
                resource.setPath("/home/ftp" + filePath + newFileName);
                resource.setExtName(oldFileName.substring(oldFileName.lastIndexOf(".")));
                resource.setCreateTime(new Date());
               return resource;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resource;
    }
}