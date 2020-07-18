package com.aaa.service;

import com.aaa.base.BaseService;
import com.aaa.mapper.AuditMapper;
import com.aaa.model.Audit;
import com.aaa.utils.BaseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.RedisProperties.*;
import static com.aaa.status.OperationStatus.*;


/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-17 10:25
 **/
@Service
public class AuditService extends BaseService<Audit> {

    @Autowired
    private AuditMapper auditMapper;


    /**
    * @Author: js.zhang
    * @Description: 通过refid查询审核记录
    * @DateTime: 2020/7/17 10:42
    * @Params: [refId]
    * @Return com.aaa.model.Audit
    */
    public Map<String,Object> selectByRefId(HashMap hashMap){
        Map<String, Object> resultMap = new HashMap<String, Object>();
        //使用pageHelper
        PageHelper.startPage(BaseUtil.transToInt(hashMap.get("pageNo")), BaseUtil.transToInt(hashMap.get("pageSize")));
        //获取refId
        Long refId = (Long) hashMap.get("refId");
        //执行方法
        List<HashMap> hashMaps = auditMapper.selectByRefId(refId);
        PageInfo<HashMap> pageInfo = new PageInfo<HashMap>(hashMaps);

        if (null != pageInfo && pageInfo.getSize() > 0) {
            resultMap.put(CODE, SUCCESS.getCode());
            resultMap.put(MSG, SUCCESS.getMsg());
            resultMap.put(DATA, pageInfo);
        } else {
            resultMap.put(CODE, FAILED.getCode());
            resultMap.put(MSG, FAILED.getMsg());
        }
        return resultMap;

    }

}
