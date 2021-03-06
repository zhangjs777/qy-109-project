package com.aaa.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.base.BaseService;
import com.aaa.base.ResultData;
import com.aaa.mapper.RoleMapper;
import com.aaa.mapper.RoleMenuMapper;
import com.aaa.model.News;
import com.aaa.model.Role;
import com.aaa.model.RoleMenu;
import com.aaa.vo.RoleVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** 
* @Description: 角色 
* @Param:
* @return:  
* @Author: ZMB 
* @Date: 2020/7/16 
*/
@Service
public class RoleService extends BaseService<Role> {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    RoleMenuMapper roleMenuMapper;

    /**
    * @Description:  查询所有的角色
    * @Param: []
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/16
    */
    public ResultData selectAllRole(){
        ResultData resultData = new ResultData();
        List<Role> roles = roleMapper.selectAll();
        if (null == roles || roles.size() <= 0){
            //说明没有查到 查到为空
            resultData.setCode("30010");
            resultData.setMsg("查询不到数据");
        }else {
            resultData.setCode("20010");
            resultData.setMsg("查询成功并且返回数据");
            resultData.setData(roles);
        }
        return resultData;
    }

    /**
    * @Description:  简单的分页查询
    * @Param: [roleVo]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/16
    */
    public ResultData selectAllRoleByPage(RoleVo roleVo){
        ResultData resultData = new ResultData();
        try {
            PageInfo<Role> rolePageInfo = super.selectListByPage(roleVo.getRole(), roleVo.getPageNo(), roleVo.getPageSize());
            if (null == rolePageInfo || "".equals(rolePageInfo)){
                //说明没有查到
                resultData.setCode("30010");
                resultData.setMsg("查询不到数据");
            }else {
                resultData.setCode("20010");
                resultData.setMsg("查询成功并且返回数据");
                resultData.setData(rolePageInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }

    /**
    * @Description:  模糊查询角色信息
    * @Param: [news]
    * @return: java.util.List<com.aaa.model.News>
    * @Author: ZMB
    * @Date: 2020/7/20
    */
    public List<Role> selectRole(Role role){
        //获取Role全部属性
        Example example = new Example(Role.class);
        Example.Criteria criteria = example.createCriteria();
        //模糊查询
        criteria.andLike("roleName","%"+role.getRoleName()+"%");
        List<Role> roleList = roleMapper.selectByExample(example);
        return roleList;
    }

    /**
    * @Description:  删除角色
    * @Param: [roleId]
    * @return: java.lang.Boolean
    * @Author: ZMB
    * @Date: 2020/7/16
    */
    public Boolean deleteRole(Long roleId){
        int i = roleMapper.deleteByPrimaryKey(roleId);
        if (i > 0){
            //说明删除成功
            // 接下来要去把role_menu表中对应的数据删掉
            //先去查他有没有权限 有权限就全部删掉  没有就结束
            List<RoleMenu> list = roleMenuMapper.selectByRoleId(roleId);
            if (list.size() > 0){
                //说明权限不是是空的  需要删除
                int i1 = roleMenuMapper.deleteRoleMenu(roleId);
                if (i1 > 0){
                    //说明删除成功
                    return true;
                }else {
                    //删除失败
                    return false;
                }
            }else {
                //说明权限是空的  不要删除
                return true;
            }
        }else {
            //删除失败  直接false
            return false;
        }

    }

    /**
    * @Description:  新增角色以及批量新增权限
    * @Param: [roleVo]
    * @return: java.lang.Boolean
    * @Author: ZMB
    * @Date: 2020/7/16
    */
    public Boolean insertRole(RoleVo roleVo){
        /*String s = DateUtil.now();*/
        roleVo.getRole().setCreateTime(new Date());

        int insert = roleMapper.insert(roleVo.getRole());
        if (insert>0) {
            //说明新增成功了
            //那就开始加roleMenu
            //如果传过来的menuId是null 说明不添加了
            //如果传来的不是空  说明需要添加roleMenu
            if (null == roleVo.getMenuId() || "".equals(roleVo.getMenuId())) {
                //说明不添加权限  只是加一个角色 返回true结束
                return true;
            } else {
                //说明需要添加新的菜单权限
                 List<RoleMenu> list = new ArrayList();
                for (Long menuId:roleVo.getMenuId()) {
                    RoleMenu rm = new RoleMenu();
                    rm.setMenuId(menuId);
                    rm.setRoleId(roleVo.getRole().getRoleId());
                    list.add(rm);
                }
                Integer i = roleMenuMapper.batchInsertRoleMenu(list);
                if (i > 0) {
                    //说明批量新增也成功了  那就返回
                    return true;
                } else {
                    return false;
                }
            }
        }
        //新增失败直接false
        return false;
    }

    /**
    * @Description:  修改角色及其权限
    * @Param: [roleVo]
    * @return: java.lang.Boolean
    * @Author: ZMB
    * @Date: 2020/7/16
    */
    public Boolean updateRole(RoleVo roleVo) {
        /*String s = DateUtil.now();*/
        roleVo.getRole().setModifyTime(new Date());
        //1、去修改role表
        int i = roleMapper.updateByPrimaryKeySelective(roleVo.getRole());
        if (i > 0) {
            //1、说明role表修改成功
            //2、要继续去修改Role的Menu
            //3、如果是没有动权限表  不执行以下流程     （在这咋判断有没有新权限）
            //4、修改就是 先删完老的， 再加新的  不管是换新的权限还是权限全部取消 都先删除 (在这判断传过来的menuId是不是null)
            //5、如果他之前没有权限就不能去删除  直接新增
            //6、如果他有权限，要撤销他的权限  就直接删除即可
            List<RoleMenu> list = roleMenuMapper.selectByRoleId(roleVo.getRole().getRoleId());
            boolean equals = list.equals(roleVo.getMenuId());
            if (true == equals) {
                //说明没有改动权限表  直接返回true
                return true;
            } else {
                //说明要改动权限表  那就先查他之前是否有权限
                List<RoleMenu> menus = roleMenuMapper.selectByRoleId(roleVo.getRole().getRoleId());
                if (menus.size() > 0) {
                    //说明以前是有权限的  无论是要给他撤销全部的权限  还是要更改他的权限  都先全部删除
                    int i2 = roleMenuMapper.deleteRoleMenu(roleVo.getRole().getRoleId());
                    if (i2 > 0) {
                        //说明权限已经全部删除了   接下来判断是否要给他换上新的权限
                        //如果传进来的权限是空的
                        if (null == roleVo.getMenuId() || "".equals(roleVo.getMenuId())) {
                            //说明没有新的权限 那就结束
                            return true;
                        } else {
                            List<RoleMenu> arr = new ArrayList();
                            for (Long mid:roleVo.getMenuId()) {
                                RoleMenu rm = new RoleMenu();
                                rm.setMenuId(mid);
                                rm.setRoleId(roleVo.getRole().getRoleId());
                                arr.add(rm);
                            }
                            int i3 = roleMenuMapper.batchInsertRoleMenu(arr);
                            if (i3>0){
                                //说明修改彻底结束
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }


}