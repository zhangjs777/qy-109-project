package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.News;
import com.aaa.service.IProjectService;
import com.aaa.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: qy-109-project
 * @author: 张梦波
 * @create: 2020-07-18 11:01
 **/
@RestController
/*@RequestMapping("/news")*/
public class NewsController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /** 
    * @Description: 信息公开的模糊查询 
    * @Param: [news] 
    * @return: com.aaa.base.ResultData 
    * @Author: ZMB 
    * @Date: 2020/7/20 
    */
    @PostMapping("/selectNews")
    public ResultData selectNewsAll(News news){
        return iProjectService.selectNewsAll(news);
    }
    
    /**
    * @Description: 查询所有信息
    * @Param: []
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    @PostMapping("/allNews")
    public ResultData selectallnews(){
        return iProjectService.selectallnews();
    }

    /**
    * @Description: 分页查询信息
    * @Param: [roleVo]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    @PostMapping("/pageNews")
    public ResultData selectAllNewsByPage(RoleVo roleVo){
        return iProjectService.selectAllNewsByPage(roleVo);
    }

    /**
    * @Description: 新增信息
    * @Param: [news]
    * @return: com.aaa.base.ResultData<com.aaa.model.News>
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    @PostMapping("/insertNews")
    public ResultData<News> insertNews(News news){
        return iProjectService.insertNews(news);
    }

    /**
    * @Description: 修改信息
    * @Param: [news]
    * @return: com.aaa.base.ResultData<com.aaa.model.News>
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    @PostMapping("/updateNews")
    public ResultData<News> updatetNews(News news){
        return iProjectService.updatetNews(news);
    }

    /**
    * @Description:  根据主键删除信息
    * @Param: [id]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    @PostMapping("/deleteNews")
    public ResultData deleteNews(long id){
        return iProjectService.deleteNews(id);
    }

}
