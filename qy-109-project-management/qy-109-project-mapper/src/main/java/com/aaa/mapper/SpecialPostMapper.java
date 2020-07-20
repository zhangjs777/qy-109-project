package com.aaa.mapper;

import com.aaa.model.SpecialPost;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface SpecialPostMapper extends Mapper<SpecialPost> {

    /**
    * @Author:xfc
    * @Description:
     *        根据单位id查出该单位的特殊岗位人员
    * @Date: 2020/7/20 10:17
    * @param id:
    * @return: java.util.List<com.aaa.model.SpecialPost>
    *
    **/
    List<SpecialPost> getSpecialPostByUnitId(Long id);
}