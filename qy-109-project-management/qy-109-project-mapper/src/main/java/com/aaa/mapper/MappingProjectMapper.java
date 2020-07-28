package com.aaa.mapper;

import com.aaa.model.MappingProject;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface MappingProjectMapper extends Mapper<MappingProject> {

    /**
    * @Author:xfc
    * @Description: 根据id进行查询  t_mapping_project表
    * @Date: 2020/7/17 9:25
    * @param id :
    * @return: com.aaa.model.MappingProject
    *
    **/
    MappingProject getMappingProjectByid(Long id);

    /**
    * @Author:xfc
    * @Description: 条件查询    通过project_name查询t_mapping_project表
    * @Date: 2020/7/17 9:25
    * @param name:
    * @return: java.util.List<HashMap<String,Object>>
    *
    **/
    List<HashMap<String,Object>> getMappingProjectByName(String name);


}