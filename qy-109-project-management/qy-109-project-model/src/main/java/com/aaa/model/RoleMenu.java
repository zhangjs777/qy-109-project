package com.aaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description:  角色菜单中间表-实体
 * @Param:
 * @return:
 * @Author: ZMB
 * @Date: 2020/7/16
 */
@Table(name = "t_role_menu")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class RoleMenu implements Serializable {
    @Column(name = "ROLE_ID")
    private Long roleId;

    @Column(name = "MENU_ID")
    private Long menuId;


}