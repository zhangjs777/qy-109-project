package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.MappingProjectMapper;
import com.aaa.model.MappingProject;
import com.aaa.redis.RedisService;
import com.aaa.utils.JSONUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import tk.mybatis.mapper.entity.Example;

import java.util.HashMap;
import java.util.List;

import static com.aaa.staticproperties.RedisProperties.EX;
import static com.aaa.staticproperties.RedisProperties.NX;

/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/17 9:39
 * @description:
 **/

@Service
public class MappingProjectService extends BaseService<MappingProject> {

    @Autowired
    private MappingProjectMapper mappingProjectMapper;

    private final static String MAPPINGPROJECT_KEY = "mappingProjectListKey";

    /**
    * @Author:xfc
    * @Description:
     *          分页查询所有测绘项目信息
    * @Date: 2020/7/17 10:11
    * @param pageNo:
     * @param pageSize:
     * @param redisService:
    * @return: com.github.pagehelper.PageInfo<com.aaa.model.MappingProject>
    *
    **/
    public PageInfo<MappingProject> getAllMappingProject(Integer pageNo, Integer pageSize, RedisService redisService){
        //1.根据前台传来的pageNo，pageSize 进行分页
        PageHelper.startPage(pageNo, pageSize);
        try {
            //从redis中获取mappingproject的key值
            //String mappingprojectkey = null;
            String mappingprojectkey = redisService.getString(MAPPINGPROJECT_KEY);
            //判断是否有key值
            if (mappingprojectkey == null || "".equals(mappingprojectkey)) {
                //但key值为空时需要从mysql里查数据
                try {
                    //从Mysql中查询数据
                    List<MappingProject> mappingProjects = mappingProjectMapper.selectAll();
                    if (0==mappingProjects.size()&&"".equals(mappingProjects)&&null==mappingProjects){
                        //说明数据库没有值
                        return null;
                    }else {
                        //说明数据库有值
                        //将值存入redis
                        try {
                            redisService.set(MAPPINGPROJECT_KEY,mappingProjects,NX, EX, 1800);
                        } catch (Exception e) {
                            //如果出现异常在存一次redis
                            redisService.set(MAPPINGPROJECT_KEY,mappingProjects,NX, EX, 1800);
                        }
                        //返回分页数据
                        return new PageInfo<MappingProject>(mappingProjects);
                    }
                } catch (Exception e) {
                    //当查询数据库异常时在进行一遍以上操作
                    // 从数据Mysql中查询
                    List<MappingProject> mappingProjects = mappingProjectMapper.selectAll();
                    if (0==mappingProjects.size()&&"".equals(mappingProjects)&&null==mappingProjects){
                        //当数据库没有值是直接返回null
                        return null;
                    }else {
                        //说明数据库有值。。将值存入redis
                        try {
                            redisService.set(MAPPINGPROJECT_KEY,mappingProjects,NX, EX, 1800);
                        } catch (Exception e1) {
                            //当存redis出现异常在存一次redis
                            redisService.set(MAPPINGPROJECT_KEY,mappingProjects,NX, EX, 1800);
                        }
                        //返回分页数据
                        return new PageInfo<MappingProject>(mappingProjects);
                    }
                }
            }else{
                //说明有key值
                //查询到了值，对数据分页，返回数据到上一层
                return new PageInfo<MappingProject>(JSONUtils.toList(MAPPINGPROJECT_KEY, MappingProject.class));
            }
        } catch (Exception e) {
            //--------------------------------------------------------------
            //当以上操作出现异常时，，在重新执行遍以上操作
            //从redis中获取mappingProject的key值
            String mappingprojectkey = redisService.getString(MAPPINGPROJECT_KEY);
            //判断是否有key值
            if (mappingprojectkey == null || "".equals(mappingprojectkey)) {
                //但key值为空时需要从mysql里查数据
                try {
                    //从数据Mysql中查询
                    List<MappingProject> mappingProjects = mappingProjectMapper.selectAll();
                    if (0==mappingProjects.size()&&"".equals(mappingProjects)&&null==mappingProjects){
                        //说明数据库没有值
                        return null;
                    }else {
                        //说明数据库有值
                        //将值存入redis
                        try {
                            redisService.set(MAPPINGPROJECT_KEY,mappingProjects,NX, EX, 1800);
                        } catch (Exception e1) {
                            //如果出现异常在存一次redis
                            redisService.set(MAPPINGPROJECT_KEY,mappingProjects,NX, EX, 1800);
                        }
                        //返回分页数据
                        return new PageInfo<MappingProject>(mappingProjects);
                    }
                } catch (Exception e2) {
                    //当查询数据库异常时在进行一遍以上操作
                    // 从数据Mysql中查询
                    List<MappingProject> mappingProjects = mappingProjectMapper.selectAll();
                    if (0==mappingProjects.size()&&"".equals(mappingProjects)&&null==mappingProjects){
                        //当数据库没有值是直接返回null
                        return null;
                    }else {
                        //说明数据库有值。。将值存入redis
                        try {
                            redisService.set(MAPPINGPROJECT_KEY,mappingProjects,NX, EX, 1800);
                        } catch (Exception e1) {
                            //当存redis出现异常在存一次redis
                            redisService.set(MAPPINGPROJECT_KEY,mappingProjects,NX, EX, 1800);
                        }
                        //返回分页数据
                        return new PageInfo<MappingProject>(mappingProjects);
                    }
                }
            }else{
                //说明有key值
                //查询到了值，对数据分页，
                return new PageInfo<MappingProject>(JSONUtils.toList(MAPPINGPROJECT_KEY, MappingProject.class));
            }
        }
    }


    /**
    * @Author:xfc
    * @Description:
     *         根据id进行查询  t_mapping_project表
    * @Date: 2020/7/17 10:12
    * @param id:
    * @return: com.aaa.model.MappingProject
    *
    **/

    public MappingProject getMappingProjectById(Long id){

        try {
            MappingProject getMappingProject = mappingProjectMapper.selectByPrimaryKey(id);
            System.out.println(getMappingProject);

            if ("".equals(getMappingProject)&& null==getMappingProject){
                //没有数据，返回null
                return null;
            }else {
                //有数据，返回数据
                return getMappingProject;
            }
        }catch (Exception e){
            MappingProject getMappingProject = mappingProjectMapper.getMappingProjectByid(id);
            if("".equals(getMappingProject) && null==getMappingProject){
                //没有数据，返回null
                return null;
            }else {
                //有数据，返回数据
                return getMappingProject;
            }
        }

    }


    /**
    * @Author:xfc
    * @Description:
     *      条件查询   通过project_name查询t_mapping_project表
    * @Date: 2020/7/17 10:38
    * @param name:
    * @return: java.util.List<java.util.HashMap<java.lang.String,java.lang.Object>>
    *
    **/
    public List<HashMap<String,Object>> getMappingProjectByName(String name){
        try {
            //通过project_name查询t_mapping_project表数据
            List<HashMap<String,Object>> mappingProjectByName = mappingProjectMapper.getMappingProjectByName(name);
            System.out.println(mappingProjectByName);
            if ("".equals(mappingProjectByName) && null== mappingProjectByName){
                return null;
            }else {
                return mappingProjectByName;
            }
        }catch (Exception e){
            //出现异常，再次查询
            // 通过project_name查询t_mapping_project表数据
            List<HashMap<String,Object>> mappingProjectByName = mappingProjectMapper.getMappingProjectByName(name);
            System.out.println(mappingProjectByName);
            if ("".equals(mappingProjectByName) && null== mappingProjectByName){
                return null;
            }else {
                return mappingProjectByName;
            }
        }
    }




    /**
    * @Author:xfc
    * @Description:
     *      根据 名称模糊查询 +条件 （类型）  查询
    * @Date: 2020/7/22 9:51
    * @param mappingProject:
    * @return: java.util.List<com.aaa.model.MappingProject>
    *
    **/

    public List<MappingProject> selectAllMappingProject(MappingProject mappingProject){
        //获取mappingProject全部属性
        Example example = new Example(MappingProject.class);
        Example.Criteria criteria = example.createCriteria();
        //拼接条件模糊查询projectName和精确查询项目类型+创建时间
        criteria.andLike("projectName","%"+mappingProject.getProjectName()+"%").andEqualTo("projectType",mappingProject.getProjectType()).andEqualTo("startDate");

        List<MappingProject> mappingProjectList = mappingProjectMapper.selectByExample(example);
        return mappingProjectList;
    }



}
