package com.aaa.controller;

import cn.hutool.core.date.DateUtil;
import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Score;
import com.aaa.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.CODE;
import static com.aaa.status.OperationStatus.*;
import static com.aaa.status.SelectStatus.*;


/**
 * @program: qy-109-project
 * @description:  单位分数表
 * @author: 张竞赛
 * @create: 2020-07-17 17:15
 **/
@RestController
public class ScoreController extends CommonController<Score> {
    @Autowired
    private ScoreService scoreService;
    @Override
    public BaseService getBaseService() {
        return scoreService;
    }

    /**
    * @Author: js.zhang
    * @Description: 通过unitID查询后分页查询
    * @DateTime: 2020/7/18 8:51
    * @Params: [hashMap]
    * @Return com.aaa.base.ResultData
    */

    @PostMapping("/selectByUnitId")
    public ResultData selectByUnitId(@RequestBody HashMap hashMap){

        Map<String, Object> stringObjectMap = scoreService.selectByUnitId(hashMap);

        if (SELECT_DATA_SUCCESS.getCode().equals(stringObjectMap.get(CODE))){
            return super.operationSuccess(stringObjectMap);
        }else if (SELECT_DATA_FAILED.getCode().equals(stringObjectMap.get(CODE))){
            return super.operationFailed();
        }else{
            return super.operationFailed(DATA_NOT_EXIST.getMsg());
        }

    }



    /**
    * @Author: js.zhang
    * @Description: 修改单位的分数
    * @DateTime: 2020/7/18 9:37
    * @Params: [map]
    * @Return com.aaa.base.ResultData
    */
    @PostMapping("/addScore")
    public ResultData addScore(@RequestBody Map map){
        //设置创建时间
        map.put("createTime", DateUtil.now());
        //执行新增
        ResultData resultData = super.add(map);
        return resultData;

    }

}
