package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.Menu;
import com.aaa.model.News;

import com.aaa.service.NewsService;
import com.aaa.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: qy-109-project
 * @author: 张梦波
 * @create: 2020-07-18 11:01
 **/
@RestController
/*@RequestMapping("/news")*/
public class NewsController extends BaseController {

    @Autowired
    private NewsService newsService;

    /** 
    * @Description: 信息公开的模糊查询 
    * @Param: [news] 
    * @return: com.aaa.base.ResultData 
    * @Author: ZMB 
    * @Date: 2020/7/20 
    */
    @PostMapping("/selectNews")
    public ResultData selectNewsAll(@RequestBody News news){
        List<News> newsList = newsService.selectNews(news);
        if (newsList !=null&&newsList.size()>0){
            return  super.operationSuccess(newsList);
        }else {
            return  super.operationFailed();
        }
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
        ResultData resultData=newsService.selectALLNews();
        if ("20010" == resultData.getCode()){
            return super.operationSuccess(resultData.getData());
        }else {
            return super.operationFailed();
        }
    }

    /**
    * @Description: 分页查询信息
    * @Param: [roleVo]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    @PostMapping("/pageNews")
    public ResultData selectAllNewsByPage(@RequestBody RoleVo roleVo){
        ResultData resultData=newsService.selectALLNewsByge(roleVo);
        if ("20010" == resultData.getCode()){
            return super.operationSuccess(resultData.getData());
        }else {
            return super.operationFailed();
        }
    }

    /**
    * @Description: 新增信息
    * @Param: [news]
    * @return: com.aaa.base.ResultData<com.aaa.model.News>
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    @PostMapping("/insertNews")
    public ResultData<News> insertNews(@RequestBody News news){
        Boolean aBoolean=newsService.addNews(news);
        if (true == aBoolean){
            return super.operationSuccess();
        }else{
            return super.operationFailed();
        }
    }

    /**
    * @Description: 修改信息
    * @Param: [news]
    * @return: com.aaa.base.ResultData<com.aaa.model.News>
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    @PostMapping("/updateNews")
    public ResultData<News> updatetNews(@RequestBody News news){
        Boolean aBoolean=newsService.updateNews(news);
        if (true == aBoolean){
            return super.operationSuccess();
        }else{
            return super.operationFailed();
        }
    }

    /**
    * @Description:  根据主键删除信息
    * @Param: [id]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    @PostMapping("/deleteNews")
    public ResultData deleteNews(@RequestParam("id") long id){
        Boolean aBoolean=newsService.deleteNews(id);
        if (true == aBoolean){
            return super.operationSuccess();
        }else{
            return super.operationFailed();
        }
    }

}
