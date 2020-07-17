package com.aaa.mapper;

import com.aaa.model.MappingProject;
import org.apache.ibatis.annotations.Select;
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
    MappingProject getMappingProjectByid(String id);

    /**
    * @Author:xfc
    * @Description: 条件查询    通过project_name查询t_mapping_project表
    * @Date: 2020/7/17 9:25
    * @param name:
    * @return: java.util.List<HashMap<String,Object>>
    *
    **/
    List<HashMap<String,Object>> getMappingProjectByName(String name);

    /**
    * @Author:xfc
    * @Description: 查询未审核的项目登记信息
    * @Date: 2020/7/17 9:26

    * @return: java.util.List<com.aaa.model.MappingProject>
    *
    **/
    @Select("SELECT distinct A.*\n" +
            "FROM t_mapping_project A \n" +
            "JOIN t_audit B     on   A.id=B.ref_id\n" +
            "WHERE B.`name`=\"项目登记审核\" AND ISNULL(B.STATUS)")
    List<MappingProject> getMappingProjectUnaudited();


    /**
    * @Author:xfc
    * @Description: 查询未审核的项目成果汇交信息
    * @Date: 2020/7/17 9:27

    * @return: java.util.List<com.aaa.model.MappingProject>
    *
    **/
    @Select("SELECT distinct A.*\n" +
            "FROM t_mapping_project A \n" +
            "JOIN t_audit B     on   A.id=B.ref_id\n" +
            "WHERE B.`name`=\"项目成果汇交\" AND ISNULL(B.STATUS)")
    List<MappingProject> getMappingProjectUnauditedInfo();



    /**
    * @Author:xfc
    * @Description:
     *      条件分页查询 根据project_name查寻未审核的项目登记信息
    * @Date: 2020/7/17 9:36
    * @param name:
    * @return: java.util.List<com.aaa.model.MappingProject>
    *
    **/

//   @Select("SELECT distinct A.*\n" +
//            "FROM t_mapping_project A \n" +
//            "JOIN t_audit B     on   A.id=B.ref_id\n" +
//            "WHERE B.`name`=\"项目登记审核\" AND ISNULL(B.STATUS)\n" +
//            "AND A.project_name=\"地形测量\"")
    List<MappingProject> getMappingProjectUnauditedByName(String name);


    /**
    * @Author:xfc
    * @Description:
     *      条件分页查询 根据project_name查寻未审核的项目成果汇交信息
    * @Date: 2020/7/17 9:37
    * @param name:
    * @return: java.util.List<com.aaa.model.MappingProject>
    *
    **/

    List<MappingProject> getMappingProjectUnauditedByNameInfo(String name);


}