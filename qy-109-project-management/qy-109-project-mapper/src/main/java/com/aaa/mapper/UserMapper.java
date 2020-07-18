package com.aaa.mapper;

import com.aaa.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.util.HashMap;
import java.util.List;

public interface UserMapper extends Mapper<User> {

    /**
    * @Author: js.zhang
    * @Description: 条件分页查询所有
    * @DateTime: 2020/7/17 18:40
    * @Params: [map]
    * @Return java.util.List<java.util.HashMap>
    */
    List<HashMap> selectUserAll(HashMap map);

}