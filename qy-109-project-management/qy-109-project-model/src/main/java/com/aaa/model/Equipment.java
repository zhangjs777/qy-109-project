package com.aaa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Description:  仪器设备信息-实体
 * @Param:
 * @return:
 * @Author: ZMB
 * @Date: 2020/7/16
 */
@Table(name = "t_equipment")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class Equipment implements Serializable {
    /**
     * 编号
     */
    @Id
    private Long id;

    /**
     * 仪器设备名称
     */
    private String name;

    /**
     * 品牌型号
     */
    private String brand;

    /**
     * 出厂编号
     */
    @Column(name = "production_id")
    private String productionId;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 检定日期
     */
    @Column(name = "check_date")
    private Date checkDate;

    /**
     * 检定有效日期
     */
    @Column(name = "effective_date")
    private Date effectiveDate;

    /**
     * 发票代码
     */
    @Column(name = "invoice_code")
    private String invoiceCode;

    /**
     * 检定机构
     */
    @Column(name = "check_department")
    private String checkDepartment;

    /**
     * 检定证书号
     */
    @Column(name = "check_certificate_id")
    private String checkCertificateId;

    /**
     * 认定
     */
    private String identified;

    /**
     * 单位用户编号
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 创建时间

     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "modify_time")
    private Date modifyTime;

}