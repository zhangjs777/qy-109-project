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
 * @Description:  信息公开-实体
 * @Param:
 * @return:
 * @Author: ZMB
 * @Date: 2020/7/16
 */
@Table(name = "t_news")
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class News implements Serializable {
    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 正文摘要
     */
    private String digest;

    /**
     * 创建时间
     */
    @Column(name = "gmt_create")
    /*private Date gmtCreate;*/
    private String gmtCreate;

    /**
     * 记录最近一次修改时间
     */
    @Column(name = "gmt_modified")
    /*private Date gmtModified;*/
    private String gmtModified;

    /**
     * 正文
     */
    private String body;

}