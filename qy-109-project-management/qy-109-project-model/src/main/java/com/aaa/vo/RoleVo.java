package com.aaa.vo;

import com.aaa.model.Dept;
import com.aaa.model.News;
import com.aaa.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
* @Description:
* @Param:
* @return:
* @Author: ZMB
* @Date: 2020/7/16
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RoleVo implements Serializable {


    private List<Long> menuId;

    private Role role;

    private Dept dept;

    private News news;

    private Integer pageNo;

    private Integer pageSize;

}