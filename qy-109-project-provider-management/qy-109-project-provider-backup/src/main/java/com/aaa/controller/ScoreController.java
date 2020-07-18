package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Score;
import com.aaa.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: qy-109-project
 * @description:
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
    //新增单位分数数据
    @PostMapping("/addScore")
    public ResultData addScore(@RequestBody Map map){
        ResultData resultData = super.add(map);
        return resultData;

    }

}
