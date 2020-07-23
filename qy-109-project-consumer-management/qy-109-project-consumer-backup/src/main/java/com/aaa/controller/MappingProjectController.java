package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingProject;
import com.aaa.redis.RedisService;
import com.aaa.service.MappingProjectService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/17 13:55
 * @description:
 **/
@RestController
public class MappingProjectController extends CommonController<MappingProject> {

    @Autowired
    MappingProjectService mappingProjectService;

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
    public ResultData getMappingProjectById(@RequestParam("id") String id){
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
     *           查询 未审核的项目登记信息
    * @Date: 2020/7/17 14:37
    * @param pageNo:
     * @param pageSize:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/getMappingProjectUnaudited")
    public ResultData getMappingProjectUnaudited(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
        PageInfo<MappingProject> mappingProjectList= mappingProjectService.getMappingProjectUnaudited(pageNo,pageSize);
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
     *          查询未审核的项目成功汇交信息
    * @Date: 2020/7/17 14:48
    * @param pageNo:
     * @param pageSize:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/getMappingProjectUnauditedInfo")
    public ResultData getMappingProjectUnauditedInfo(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){
        PageInfo<MappingProject> mappingProjectList= mappingProjectService.getMappingProjectUnauditedInfo(pageNo,pageSize);
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
     *         根据project_name查询未审核的项目登记信息
    * @Date: 2020/7/17 14:51
    * @param pageNo:
     * @param pageSize:
     * @param name:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/getMappingProjectUnauditedByName")
    public ResultData getMappingProjectUnauditedByName(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize")Integer pageSize,@RequestParam("name") String name){
        PageInfo<MappingProject> mappingProjectList= mappingProjectService.getMappingProjectUnauditedByName(pageNo,pageSize,name);
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
     *           根据project_name查询未审核的项目成果汇交信息
    * @Date: 2020/7/17 14:55
    * @param pageNo:
     * @param pageSize:
     * @param name:
    * @return: com.aaa.base.ResultData
    *
    **/

    @PostMapping("/getMappingProjectUnauditedByNameInfo")
    public ResultData getMappingProjectUnauditedByNameInfo(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize")Integer pageSize,@RequestParam("name") String name){
        PageInfo<MappingProject> mappingProjectList= mappingProjectService.getMappingProjectUnauditedByNameInfo(pageNo,pageSize,name);
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
     *      条件 查询
    * @Date: 2020/7/22 11:38
    * @param mappingProject:
    * @return: com.aaa.base.ResultData
    *
    **/
    @RequestMapping("/selectAllMappingProject")

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


}
