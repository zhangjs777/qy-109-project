package com.aaa.mapper;

import com.aaa.model.RoleMenu;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Description: 角色菜单中间表-通用mapper
 * @Param:
 * @return:
 * @Author: ZMB
 * @Date: 2020/7/16
 */
public interface RoleMenuMapper extends Mapper<RoleMenu> {
    /**
     * @Description: 在RoleMenu表中根据roleId删除
     * @Author: guohang
     * @Date: 2020/6/3 19:03
     * @Param: [roleId]
     * @return: int
     */
    int deleteRoleMenu(Long roleId);

    /**
     * @Description: 批量新增
     * @Author: guohang
     * @Date: 2020/6/3 19:03
     * @Param: [roleMenus]
     * @return: int
     */
    int batchInsertRoleMenu(List<RoleMenu> roleMenus);

    /**
     * @Description: 查询表里面有没有roleId
     * @Author: guohang
     * @Date: 2020/6/3 19:03
     * @Param: [roleId]
     * @return: java.util.List<com.aaa.qy108.model.RoleMenu>
     */
    List<RoleMenu> selectByRoleId(Long roleId);
}