package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.base.ResultData;
import com.aaa.model.Dictionary;
import com.aaa.redis.RedisService;
import com.aaa.service.DictService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/16 11:21
 * @description:
 **/

@RestController
public class DictController extends CommonController<Dictionary> {

    @Autowired
    private DictService dictService;

    @Autowired
    private RedisService redisService;


    @Override
    public BaseService<Dictionary> getBaseService() {
        return dictService;
    }


    /**
    * @Author:xfc
    * @Description:
     *         查询全部字典数据信息
    * @Date:2020/7/16
    * @param pageNo:
     * @param pageSize:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/getAllDict")
    public ResultData getAllDict(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize){

        PageInfo<Dictionary> dictList=dictService.getAllDict(pageNo,pageSize,redisService);
        if (!"".equals(dictList) && null !=dictList){
            //（true）有值，返回成功结果
            return operationSuccess(dictList);
        }else {
            //(false)无值，返回失败结果
            return operationFailed();
        }

    }

    /**
    * @Author:xfc
    * @Description:
    * @Date:2020/7/16
    * @param dictionary:
     *          新增数据字典数据
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/addDict")
    public Boolean addDict(@RequestBody Dictionary dictionary)throws Exception{
        //调用service层新增方法
        Boolean result=dictService.addDict(dictionary,redisService);
        //返回结果
        return result;
    }


    /**
    * @Author:xfc
    * @Description:
     *          删除字典信息
    * @Date:2020/7/16
    * @param dictionary:
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/delDict")
    public Boolean delDict(@RequestBody Dictionary dictionary)throws Exception{
        //调用service层删除方法
        Boolean result=dictService.delDict(dictionary,redisService);
        //返回结果
        return result;
    }

    /**
    * @Author:xfc
    * @Description:
     *      更新字典数据
    * @Date:2020/7/16
    * @param dictionary:
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/updateDict")
    public Boolean updateDict(@RequestBody Dictionary dictionary)throws Exception{
        //调用service层更新方法
        Boolean result=dictService.updateDict(dictionary,redisService);
        //返回结果
        return result;
    }

}
