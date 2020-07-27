package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.Dictionary;
import com.aaa.service.IProjectService;
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
public class DictController extends BaseController {

    @Autowired
    private IProjectService iProjectService;


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
        public ResultData getAllDict(@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize")Integer pageSize){
        return iProjectService.getAllDict(pageNo,pageSize);

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
    public Boolean addDict(@RequestBody  Dictionary dictionary){
      return iProjectService.addDict(dictionary);
    }


    /**
    * @Author:xfc
    * @Description:
     *          删除 字典信息
    * @Date:2020/7/16
    * @param dictionary:
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/delDict")
    public Boolean delDict(@RequestBody Dictionary dictionary){
        return iProjectService.delDict(dictionary);
    }


    /**
    * @Author:xfc
    * @Description:
     *    更新数据字典
    * @Date: 2020/7/23 21:07
    * @param dictionary:
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/updateDict")
    public Boolean updateDict(@RequestBody Dictionary dictionary){
        return iProjectService.updateDict(dictionary);
    }



}
