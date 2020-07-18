package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.ScoreMapper;
import com.aaa.model.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.*;
import static com.aaa.status.AddStatus.*;

/**
 * @program: qy-109-project
 * @description: 单位分数记录表
 * @author: 张竞赛
 * @create: 2020-07-17 16:35
 **/
@Service
public class ScoreService extends BaseService<Score>{

}
