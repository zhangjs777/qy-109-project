package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.MappingProject;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/17 13:55
 * @description:
 **/
@RestController
public class MappingProjectController extends BaseController {

    @Autowired
   private IProjectService iProjectService;


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
       return iProjectService.getAllMappingProject(pageNo,pageSize);
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
       return iProjectService.getMappingProjectByName(name);
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
      return iProjectService.getMappingProjectById(id);
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
        return iProjectService.selectAllMappingProject(mappingProject);
    }

    /**
    * @Author:xfc
    * @Description:
     *     根据输入参数查询信息
    * @Date: 2020/7/28 10:39
    * @param mappingProject:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/select")
    public  ResultData select(@RequestBody MappingProject mappingProject){
        return iProjectService.select(mappingProject);
    }
}
