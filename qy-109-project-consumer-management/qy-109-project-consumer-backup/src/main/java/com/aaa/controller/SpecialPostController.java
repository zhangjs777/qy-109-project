package com.aaa.controller;

import com.aaa.base.BaseService;
import com.aaa.base.CommonController;
import com.aaa.model.SpecialPost;
import com.aaa.service.SpecialPostServcie;
import com.github.pagehelper.PageInfo;
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
public class SpecialPostController extends CommonController<SpecialPost> {

    @Autowired
    private SpecialPostServcie specialPostServcie;

    @Override
    public BaseService<SpecialPost> getBaseService() {

        return specialPostServcie;
    }

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
    public PageInfo<SpecialPost> getAllSpecialPost(@RequestBody SpecialPost specialPost,@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize")Integer pageSize) {
        PageInfo<SpecialPost> sPageInfo = getBaseService().selectListByPage(specialPost, pageNo, pageSize);
        return (null != sPageInfo && sPageInfo.getSize() > 0) ? sPageInfo : null;
    }

   /**
   * @Author:xfc
   * @Description:
    *        添加特殊人才信息
   * @Date: 2020/7/20 20:45
   * @param specialPost:
   * @return: java.lang.Boolean
   *
   **/
    @PostMapping("/addSpecialPost")
    public Boolean addSpecialPost(@RequestBody SpecialPost specialPost)throws Exception{
        Boolean result=specialPostServcie.addSpecialPost(specialPost);
        return result;
    }


    /**
    * @Author:xfc
    * @Description:
     *       更新特殊人才信息
    * @Date: 2020/7/20 20:45
    * @param specialPost:
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/updateSpecialPost")
    public Boolean updateSpecialPost(@RequestBody SpecialPost specialPost)throws Exception{
        Boolean result=specialPostServcie.updateSpecialPost(specialPost);
        return result;
    }


    /**
    * @Author:xfc
    * @Description:
     *     根据id 删除特殊人才信息
    * @Date: 2020/7/20 20:46
    * @param id:
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/deleteSpecialPost")
    public Boolean deleteSpecialPost(@RequestParam("id") Long id)throws Exception{
        Boolean result=specialPostServcie.deleteSpecialPost(id);
        return result;
    }


}
