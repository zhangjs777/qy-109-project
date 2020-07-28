package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingProject;
import com.aaa.model.User;
import com.aaa.redis.RedisService;
import com.aaa.service.MappingProjectService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/17 13:55
 * @description:
 **/
@RestController
public class MappingProjectController extends CommonController<MappingProject> {

    @Autowired
    private MappingProjectService mappingProjectService;

    @Autowired
    private RedisService redisService;


    @Override
    public BaseService<MappingProject> getBaseService() {
        return mappingProjectService;
    }


    /**
    * @Author:xfc
    * @Description:
     *           分页查询所有测绘项目信息
    * @Date: 2020/7/17 14:23
    * @param pageNo:
     * @param pageSize:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/getAllMappingProject")
    public ResultData getAllMappingProject(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageInfo<MappingProject> mappingProjectList= mappingProjectService.getAllMappingProject(pageNo,pageSize,redisService);
        if (!"".equals(mappingProjectList) && null !=mappingProjectList){
            //（true）有值，返回成功结果
            return operationSuccess(mappingProjectList);
        }else {
            //(false)无值，返回失败结果
            return operationFailed();
        }
    }

    /**
    * @Author:xfc
    * @Description:
     *          通过project_name查询t_mapping_project表
    * @Date: 2020/7/17 15:09
    * @param name:
    * @return: com.aaa.base.ResultData
    *
    **/
   @PostMapping("/getMappingProjectByName")
   public ResultData getMappingProjectByName(@RequestParam("name") String name){
        System.out.println(name);
        List<HashMap<String, Object>> mappingProjectList = mappingProjectService.getMappingProjectByName(name);
        if (!"".equals(mappingProjectList) && null !=mappingProjectList){
            //（true）有值，返回成功结果
            return operationSuccess(mappingProjectList);
        }else {
            //(false)无值，返回失败结果
            return operationFailed();
        }

   }

   /**
   * @Author:xfc
   * @Description:
    *           通过id查询测绘工程的详细信息
   * @Date: 2020/7/17 15:13
   * @param id:
   * @return: com.aaa.base.ResultData
   *
   **/
   @PostMapping("/getMappingProjectById")
    public ResultData getMappingProjectById(@RequestParam("id") Long id){
       MappingProject mappingProjectById = mappingProjectService.getMappingProjectById(id);
       if (!"".equals(mappingProjectById) && null !=mappingProjectById){
            //（true）有值，返回成功结果
            return operationSuccess(mappingProjectById);
        }else {
            //(false)无值，返回失败结果
            return operationFailed();
        }
    }



    /**
    * @Author:xfc
    * @Description:
     *      条件 查询
    * @Date: 2020/7/22 11:38
    * @param mappingProject:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/selectAllMappingProject")

    public  ResultData selectAllMappingProject(@RequestBody MappingProject mappingProject) {
         List<MappingProject> mappingProjectList= mappingProjectService.selectAllMappingProject(mappingProject);

        if (!"".equals(mappingProjectList) && null !=mappingProjectList){
            //（true）有值，返回成功结果
            return operationSuccess(mappingProjectList);
        }else {
            //(false)无值，返回失败结果
            return operationFailed();
        }
    }

   /**
   * @Author:xfc
   * @Description:
    *     根据参数查询信信息
   * @Date: 2020/7/28 10:32
   * @param mappingProject:
   * @return: java.util.List<com.aaa.model.MappingProject>
   *
   **/
    @PostMapping("/select")
    public ResultData select(MappingProject mappingProject){
        List<MappingProject> mappingProjectList = getBaseService().selectList(mappingProject);
        if (!"".equals(mappingProjectList) && null !=mappingProjectList){
            //（true）有值，返回成功结果
            System.out.println("反序列化数据"+mappingProjectList);
            return operationSuccess(mappingProjectList);
        }else {
            //(false)无值，返回失败结果
            return operationFailed();
        }
    }

}
