package com.aaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
* @Description:  部门-实体
* @Param:
* @return:
* @Author: ZMB
* @Date: 2020/7/16
*/
@Table(name = "t_dept")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class Dept {
    /**
     * 部门ID
     */
    @Id
    @Column(name = "DEPT_ID")
    private Long deptId;

    /**
     * 上级部门ID
     */
    @Column(name = "PARENT_ID")
    private Long parentId;

    /**
     * 部门名称
     */
    @Column(name = "DEPT_NAME")
    private String deptName;

    /**
     * 排序
     */
    @Column(name = "ORDER_NUM")
    private Double orderNum;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "MODIFY_TIME")
    private Date modifyTime;

}