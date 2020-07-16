package com.aaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:  角色-实体
 * @Param:
 * @return:
 * @Author: ZMB
 * @Date: 2020/7/16
 */
@Table(name = "t_role")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class Role implements Serializable {
    /**
     * 角色ID
     */
    @Id
    @Column(name = "ROLE_ID")
    private Long roleId;

    /**
     * 角色名称
     */
    @Column(name = "ROLE_NAME")
    private String roleName;

    /**
     * 角色描述
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    /*private Date createTime;*/
    private String createTime;

    /**
     * 修改时间
     */
    @Column(name = "MODIFY_TIME")
    /*private Date modifyTime;*/
    private String modifyTime;

}