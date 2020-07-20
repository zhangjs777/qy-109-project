package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.TechnicistMapper;
import com.aaa.model.Technicist;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.*;
import static com.aaa.status.SelectStatus.SELECT_DATA_FAILED;
import static com.aaa.status.SelectStatus.SELECT_DATA_SUCCESS;

/**
 * @program: qy-109-project
 * @description: 技术人员表
 * @author: 张竞赛
 * @create: 2020-07-18 10:50
 **/
@Service
public class TechnicistService extends BaseService<Technicist> {
    @Autowired
    private TechnicistMapper technicistMapper;

   /**
   * @Author: js.zhang
   * @Description: 通过id查询技术人员个人信息
   * @DateTime: 2020/7/18 15:06
   * @Params: [id]
   * @Return java.util.Map<java.lang.String,java.lang.Object>
   */
    public Map<String,Object> selectTecById(Long id){

        Map<String, Object> resultMap = new HashMap<String, Object>();

        Technicist technicist = technicistMapper.selectTechnicistById(id);
        if (technicist != null && !"".equals(technicist)){
            resultMap.put(CODE,SELECT_DATA_SUCCESS.getCode());
            resultMap.put(MSG,SELECT_DATA_SUCCESS.getMsg());
            resultMap.put(DATA,technicist);
        }else{
            resultMap.put(CODE,SELECT_DATA_FAILED.getCode());
            resultMap.put(MSG,SELECT_DATA_FAILED.getMsg());
        }
        return resultMap;

    }




   /**
   * @Author: js.zhang
   * @Description: 通过userId查询单位技术人员列表
   * @DateTime: 2020/7/18 14:20
   * @Params: [technicist, pageNo, pageSize]
   * @Return java.util.Map<java.lang.String,java.lang.Object>
   */
    public Map<String,Object> selectTechByUid(Technicist technicist,Integer pageNo,Integer pageSize){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //注入pageNo，pagesize
        PageHelper.startPage(pageNo,pageSize);

        List<Technicist> technicistList = super.selectList(technicist);
        //查出数据后分页
        PageInfo<Technicist> technicistPageInfo = new PageInfo<Technicist>(technicistList);


        if (null != technicistPageInfo  && technicistPageInfo.getSize() > 0) {
            resultMap.put(CODE, SELECT_DATA_SUCCESS.getCode());
            resultMap.put(MSG, SELECT_DATA_SUCCESS.getMsg());
            resultMap.put(DATA, technicistPageInfo);
        } else {
            resultMap.put(CODE,  SELECT_DATA_FAILED.getCode());
            resultMap.put(MSG,  SELECT_DATA_FAILED.getMsg());
        }
        return resultMap;

    }


}
