package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.ScoreMapper;
import com.aaa.model.Score;
import com.aaa.utils.BaseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.*;
import static com.aaa.status.AddStatus.*;
import static com.aaa.status.OperationStatus.FAILED;
import static com.aaa.status.OperationStatus.SUCCESS;
import static com.aaa.status.SelectStatus.*;

/**
 * @program: qy-109-project
 * @description: 单位分数记录表
 * @author: 张竞赛
 * @create: 2020-07-17 16:35
 **/
@Service
public class ScoreService extends BaseService<Score>{
    @Autowired
    private ScoreMapper scoreMapper;

    public Map<String,Object> selectByUnitId(HashMap hashMap){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //使用pageHelper
        PageHelper.startPage(BaseUtil.transToInt(hashMap.get("pageNo")), BaseUtil.transToInt(hashMap.get("pageSize")));
        //首先条件查询
        List<HashMap> hashMapList = scoreMapper.selectByUnitId(hashMap);

        PageInfo<HashMap> pageInfo = new PageInfo<HashMap>(hashMapList);
            if (null != pageInfo && pageInfo.getSize() > 0) {
                resultMap.put(CODE, SELECT_DATA_SUCCESS.getCode());
                resultMap.put(MSG, SELECT_DATA_SUCCESS.getMsg());
                resultMap.put(DATA, pageInfo);
            } else {
                resultMap.put(CODE,  SELECT_DATA_FAILED.getCode());
                resultMap.put(MSG,  SELECT_DATA_FAILED.getMsg());
            }
            return resultMap;
    }


}
