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

    public MappingProject getMappingProjectById(String id){

        try {
            MappingProject getMappingProjectById = mappingProjectMapper.getMappingProjectByid(id);
            if ("".equals(getMappingProjectById)&& null==getMappingProjectById){
                //没有数据，返回null
                return null;
            }else {
                //有数据，返回数据
                return getMappingProjectById;
            }
        }catch (Exception e){
            MappingProject getMappingProjectById = mappingProjectMapper.getMappingProjectByid(id);
            if("".equals(getMappingProjectById) && null==getMappingProjectById){
                //没有数据，返回null
                return null;
            }else {
                //有数据，返回数据
                return getMappingProjectById;
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
     *              查询未审核的项目登记信息
    * @Date: 2020/7/17 10:55
    * @param pageNo:
     * @param pageSize:
    * @return: com.github.pagehelper.PageInfo<com.aaa.model.MappingProject>
    *
    **/
    public PageInfo<MappingProject> getMappingProjectUnaudited(Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        try {
            //从mysql中查询数据
            List<MappingProject> mappingProjects =mappingProjectMapper.getMappingProjectUnaudited();
            System.out.println(mappingProjects);
            if ("".equals(mappingProjects) && 0 == mappingProjects.size() && null == mappingProjects){
                //没有数据信息
                return null;
            }else {
                return new PageInfo<MappingProject>(mappingProjects);
            }
        }catch (Exception e){
            //查询数据出现异常，再次执行以上查询操作
            List<MappingProject> mappingProjects =mappingProjectMapper.getMappingProjectUnaudited();
            System.out.println(mappingProjects);
            if ("".equals(mappingProjects) && 0 == mappingProjects.size() && null == mappingProjects){
                //没有数据信息
                return null;
            }else {
                return new PageInfo<MappingProject>(mappingProjects);
            }
        }
    }


    /**
    * @Author:xfc
    * @Description:
     *          查询未审核的项目成功汇交信息
    * @Date: 2020/7/17 11:02
    * @param pageNo:
     * @param pageSize:
    * @return: com.github.pagehelper.PageInfo<com.aaa.model.MappingProject>
    *
    **/
    public PageInfo<MappingProject> getMappingProjectUnauditedInfo(Integer pageNo, Integer pageSize){
        PageHelper.startPage(pageNo, pageSize);
        try {
            //从mysql中查询数据
            List<MappingProject> mappingProjects = mappingProjectMapper.getMappingProjectUnauditedInfo();
            System.out.println(mappingProjects);
            if ("".equals(mappingProjects) && 0 == mappingProjects.size() && null == mappingProjects){
                //没有数据信息
                return null;
            }else {
                return new PageInfo<MappingProject>(mappingProjects);
            }
        }catch (Exception e){
            //从mysql中查询数据
            List<MappingProject> mappingProjects = mappingProjectMapper.getMappingProjectUnauditedInfo();
            System.out.println(mappingProjects);
            if ("".equals(mappingProjects) && 0 == mappingProjects.size() && null == mappingProjects){
                //没有数据信息
                return null;
            }else {
                return new PageInfo<MappingProject>(mappingProjects);
            }
        }

    }


    /**
    * @Author:xfc
    * @Description:
     *          条件分页查询 根据project_name查询未审核的项目登记信息
    * @Date: 2020/7/17 11:34
    * @param pageNo:
     * @param pageSize:
     * @param name:
    * @return: com.github.pagehelper.PageInfo<com.aaa.model.MappingProject>
    *
    **/
    public PageInfo<MappingProject> getMappingProjectUnauditedByName(Integer pageNo, Integer pageSize, String name) {
        PageHelper.startPage(pageNo, pageSize);
        try {
            //从mysql中查询数据
            List<MappingProject> mappingProjects=mappingProjectMapper.getMappingProjectUnauditedByName(name);
            System.out.println(mappingProjects);
            if ("".equals(mappingProjects) && 0 == mappingProjects.size() && null == mappingProjects){
                //没有数据信息
                return null;
            }else {
                return new PageInfo<MappingProject>(mappingProjects);
            }
        }catch (Exception e){
            List<MappingProject> mappingProjects=mappingProjectMapper.getMappingProjectUnauditedByName(name);
            System.out.println(mappingProjects);
            if ("".equals(mappingProjects) && 0 == mappingProjects.size() && null == mappingProjects){
                //没有数据信息
                return null;
            }else {
                return new PageInfo<MappingProject>(mappingProjects);
            }
        }

    }



    /**
    * @Author:xfc
    * @Description:
     *            条件分页查询 根据project_name查询未审核的项目成果汇交信息
    * @Date: 2020/7/17 11:40
    * @param pageNo:
     * @param pageSize:
     * @param name:
    * @return: com.github.pagehelper.PageInfo<com.aaa.model.MappingProject>
    *
    **/
    public PageInfo<MappingProject> getMappingProjectUnauditedByNameInfo(Integer pageNo, Integer pageSize, String name){
          PageHelper.startPage(pageNo, pageSize);
        try {
            //从mysql中查询数据
            List<MappingProject> mappingProjects=mappingProjectMapper.getMappingProjectUnauditedByNameInfo(name);
            System.out.println(mappingProjects);
            if ("".equals(mappingProjects) && 0 == mappingProjects.size() && null == mappingProjects){
                //没有数据信息
                return null;
            }else {
                return new PageInfo<MappingProject>(mappingProjects);
            }
        }catch (Exception e){
            //从mysql中查询数据
            List<MappingProject> mappingProjects=mappingProjectMapper.getMappingProjectUnauditedByNameInfo(name);
            System.out.println(mappingProjects);
            if ("".equals(mappingProjects) && 0 == mappingProjects.size() && null == mappingProjects){
                //没有数据信息
                return null;
            }else {
                return new PageInfo<MappingProject>(mappingProjects);
            }
        }
    }

}
