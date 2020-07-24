package com.aaa.controller;

import com.aaa.base.ResultData;
import com.aaa.model.Score;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-23 19:37
 **/
@RestController
public class ScoreController {
    @Autowired
    private IProjectService iProjectService;


    /**
     * @Author: js.zhang
     * @Description: 通过unitID查询后分页查询
     * @DateTime: 2020/7/18 8:51
     * @Params: [hashMap]
     * @Return com.aaa.base.ResultData
     */

    @PostMapping("/selectByUnitId")
    public ResultData selectByUnitId(   HashMap hashMap){
        return iProjectService.selectByUnitId(hashMap);
    }

    /**
     * @Author: js.zhang
     * @Description: 修改单位的分数
     * @DateTime: 2020/7/18 9:37
     * @Params: [map]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/addScore")
    public ResultData addScore(   Score score,  Long id){
        return iProjectService.addScore(score,id);
    }

}
