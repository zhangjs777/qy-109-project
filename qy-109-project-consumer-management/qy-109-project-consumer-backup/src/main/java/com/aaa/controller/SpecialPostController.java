package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.SpecialPost;
import com.aaa.service.IProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @program: qy-109-project
 * @author: xfc
 * @create: 2020/7/20 18:34
 * @description:
 **/

@RestController
public class SpecialPostController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /**
    * @Author:xfc
    * @Description:
     *       查询 全部特殊人才信息
    * @Date: 2020/7/20 20:02
    * @param specialPost:
     * @param pageNo:
     * @param pageSize:
    * @return: com.github.pagehelper.PageInfo<com.aaa.model.SpecialPost>
    *
    **/
    @PostMapping("/getAllSpecialPost")
    public ResultData getAllSpecialPost(@RequestBody SpecialPost specialPost, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize) {
       return iProjectService.getAllSpecialPost(specialPost,pageNo,pageSize);
    }

   /**
   * @Author:xfc
   * @Description:
    *        添加特殊人才信息
   * @Date: 2020/7/20 20:45
   * @param specialPost:
   * @return: java.lang.Boolean
   **/

    @PostMapping("/addSpecialPost")
    public Boolean addSpecialPost(@RequestBody SpecialPost specialPost) throws Exception{
      return iProjectService.addSpecialPost(specialPost);
    }


    /**
    * @Author:xfc
    * @Description:
     *       更新特殊人才信息
    * @Date: 2020/7/20 20:45
    * @param specialPost:
    * @return: java.lang.Boolean
    **/

    @PostMapping("/updateSpecialPost")
    public Boolean updateSpecialPost(@RequestBody SpecialPost specialPost)throws Exception{
       return iProjectService.updateSpecialPost(specialPost);
    }


    /**
    * @Author:xfc
    * @Description:
     *     根据id删除特殊人才信息
    * @Date: 2020/7/20 20:46
    * @param id:
    * @return: java.lang.Boolean
    **/

    @PostMapping("/deleteSpecialPost")
    public Boolean deleteSpecialPost(@RequestParam("id") Long id)throws Exception{
        return iProjectService.deleteSpecialPost(id);
    }


}
