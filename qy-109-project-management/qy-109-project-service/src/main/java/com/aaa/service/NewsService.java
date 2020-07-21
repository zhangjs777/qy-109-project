package com.aaa.service;

import cn.hutool.core.date.DateUtil;
import com.aaa.base.BaseService;
import com.aaa.base.ResultData;
import com.aaa.mapper.NewsMapper;
import com.aaa.model.Menu;
import com.aaa.model.News;
import com.aaa.vo.RoleVo;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


import java.util.Date;
import java.util.List;

/**
 * @program: qy-109-project
 * @author: 张梦波
 * @create: 2020-07-18 09:21
 **/
@Service
public class NewsService extends BaseService<News> {

    @Autowired
    NewsMapper newsMapper;

    /**
    * @Description: 信息公开的模糊查询
    * @Param: [news]
    * @return: java.util.List<com.aaa.model.News>
    * @Author: ZMB
    * @Date: 2020/7/20
    */
    public List<News> selectNews(News news){
        //获取News全部属性
        Example example = new Example(News.class);
        Example.Criteria criteria = example.createCriteria();
        //模糊查询
        criteria.andLike("title","%"+news.getTitle()+"%");
        List<News> newsList = newsMapper.selectByExample(example);
        return newsList;
    }

    /**
    * @Description: 查询所有信息
    * @Param: []
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    public ResultData selectALLNews(){
        ResultData resultData=new ResultData();
        List<News> news=newsMapper.selectAll();
        if (null == news || news.size()<=0){
            //说明没有查到 查到为空
            resultData.setCode("30010");
            resultData.setMsg("查询不到数据");
        }else {
            resultData.setCode("20010");
            resultData.setMsg("查询成功并且返回数据");
            resultData.setData(news);
        }
        return resultData;
    }

    /**
    * @Description: 分页查询数据
    * @Param: [roleVo]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    public ResultData selectALLNewsByge(RoleVo roleVo){
        ResultData resultData=new ResultData();
        try {
            PageInfo<News> newsPageInfo=super.selectListByPage(roleVo.getNews(),roleVo.getPageNo(),roleVo.getPageSize());
            if (null == newsPageInfo || "".equals(newsPageInfo)){
                //说明没有查到
                resultData.setCode("30010");
                resultData.setMsg("查询不到数据");
            }else{
                resultData.setCode("20010");
                resultData.setMsg("查询成功并且返回数据");
                resultData.setData(newsPageInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultData;
    }


    /**
    * @Description:  信息新增
    * @Param: [news]
    * @return: java.lang.Boolean
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    public Boolean addNews(News news){
        /*String createTime= DateUtil.now();*/
        //设置新增时间
        news.setGmtCreate(new Date());
        try {
            Integer add=super.add(news);
            if (add>0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
    * @Description: 修改部门信息
    * @Param: [news]
    * @return: java.lang.Boolean
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    public Boolean updateNews(News news){
        /*String createTime= DateUtil.now();*/
        //设置修改时间
        news.setGmtModified(new Date());
        try {
            Integer update=super.update(news);
            if (update>0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
    * @Description: 根据主键删除信息
    * @Param: [id]
    * @return: java.lang.Boolean
    * @Author: ZMB
    * @Date: 2020/7/18
    */
    public Boolean deleteNews(Long id){
        int i= newsMapper.deleteByPrimaryKey(id);
        if (i>0){
            return true;
        }else{
            return false;
        }
    }




}
