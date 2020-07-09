package com.aaa.base;

import com.aaa.utils.Map2BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.aaa.staticproperties.OrderStatic.*;

/**
 * @program:
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-08 17:10
 **/
public abstract class BaseService<T>  {
    private Class<T> cache =null;

    @Autowired
    private Mapper<T> mapper;

    public Mapper getMapper() {
        return mapper;
    }

    /**
    * @Author: js.zhang
    * @Description: 新增数据
    * @DateTime: 2020/7/9 15:45
    * @Params: [t]
    * @Return java.lang.Integer
    */
    public  Integer add(T t){
        return mapper.insert(t);
    }
    /**
    * @Author: js.zhang
    * @Description: 根据主键删除
    * @DateTime: 2020/7/9 15:45
    * @Params: [t]
    * @Return java.lang.Integer
    */
    public Integer delete(T t){
        return mapper.deleteByPrimaryKey(t);
    }

    /**
    * @Author: js.zhang
    * @Description: 根据主键批量删除
    * @DateTime: 2020/7/9 16:03
    * @Params: [ids]
    * @Return java.lang.Integer
    */
    public Integer deleteByIds(List<Integer> ids){
        Example example=Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id",ids)).build();
        return mapper.deleteByExample(example);
    }

    /**
    * @Author: js.zhang
    * @Description:获取子类泛型类型
    * @DateTime: 2020/7/9 15:55
    * @Params: []
    * @Return java.lang.Class<T>
    */
    public Class<T> getTypeArgument(){
     if (null == cache){
        cache=(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
     }
     return cache;
    }


    /**
    * @Author: js.zhang
    * @Description: 根据id更新
    * @DateTime: 2020/7/9 16:09
    * @Params: [t]
    * @Return java.lang.Integer
    */
    public Integer update(T t){
        return mapper.updateByPrimaryKey(t);
    }
    /**
    * @Author: js.zhang
    * @Description: 批量更新
    * @DateTime: 2020/7/9 16:07
    * @Params: [t, ids]
    * @Return java.lang.Integer
    */
    public Integer batchUpdate(T t,Integer [] ids){
       Example example= Example.builder(getTypeArgument()).where(Sqls.custom().andIn("id", Arrays.asList(ids))).build();
        return mapper.updateByExample(t,example);
    }
    
    /**
    * @Author: js.zhang
    * @Description: 查询一条数据
    * @DateTime: 2020/7/9 16:12
    * @Params: [t]
    * @Return T
    */
    public T selectOne(T t){
        return mapper.selectOne(t);
    }

    /**
    * @Author: js.zhang
    * @Description: 查询一条数据 可以排序
    * @DateTime: 2020/7/9 16:43
    * @Params: [where, orderByFiled, fileds]
    * @Return T
    */
    public T selectOneByFiled(Sqls where,String orderByFiled, String... fileds){
        return selectByFileds(null,null,where,orderByFiled,null,fileds).get(0);

    }

    /**
    * @Author: js.zhang
    * @Description: 通过条件查询一个列表
    * @DateTime: 2020/7/9 16:50
    * @Params: [where, orderByFiled, fileds]
    * @Return java.util.List<T>
    */
  public List<T> selectListByFiled(Sqls where,String orderByFiled,String... fileds){
        return  selectByFileds(null,null,where,orderByFiled,null,fileds);
  }


  /**
  * @Author: js.zhang
  * @Description: 实现条件查询后分页
  * @DateTime: 2020/7/9 16:55
  * @Params: [pageNO, pageSize, where, orderFiled, fileds]
  * @Return com.github.pagehelper.PageInfo<T>
  */
  public PageInfo<T> selectListPageAndFiled(Integer pageNO,Integer pageSize,Sqls where,String orderFiled,String... fileds){
        return new PageInfo<T>(selectByFileds(pageNO,pageSize,where,orderFiled,null,fileds));

  }

/**
* @Author: js.zhang
* @Description: 查询集合 条件查询
* @DateTime: 2020/7/9 16:56
* @Params: [t]
* @Return java.util.List<T>
*/
  public List<T> selectList(T t){
      return mapper.select(t);
  }
    public PageInfo<T> selectListByPage(T t,Integer pageNo,Integer pageSize){
      PageHelper.startPage(pageNo,pageSize);
      List<T> select=mapper.select(t);
      PageInfo<T> pageInfo=new PageInfo<T>(select);
      return pageInfo;
    }
    /**
    * @Author: js.zhang
    * @Description: map转实体类
    * @DateTime: 2020/7/9 18:33
    * @Params: [map]
    * @Return T
    */

    public  T newInstance(Map map){
      return (T) Map2BeanUtils.map2Bean(map,getTypeArgument());
    }

    /**
    * @Author: js.zhang
    * @Description: 实现查询通用，分页 排序，多条件查询
     * oderByFiled:是所要排序的字段
    * @DateTime: 2020/7/9 16:40
    * @Params: [pageNo, pageSize, where, orderByFiled, orderWord, fileds]
    * @Return java.util.List<T>
    */
    private  List<T> selectByFileds(Integer pageNo,Integer pageSize,Sqls where,String orderByFiled,String orderWord,String... fileds){
        Example.Builder builder=null;
        if (null== fileds || fileds.length == 0){
            //查询所有
            builder=Example.builder(getTypeArgument());
        }else {
            //条件查询
            builder=Example.builder(getTypeArgument()).select(fileds);

        }
        if (where !=null){
            //说明需要进行条件查询
            builder=builder.where(where);
        }
        if (orderByFiled !=null){
            //说明需要排序
            if (DESC.equals(orderWord.toUpperCase())){
                builder=builder.orderByDesc(orderByFiled);
            }else if(ASC.equals(orderWord.toUpperCase())){
                builder=builder.orderByAsc(orderByFiled);
            }else {
                builder=builder.orderByDesc(orderByFiled);
            }
        }
        Example example=builder.build();
        //分页
        if (pageNo !=null & pageSize !=null){
            PageHelper.startPage(pageNo, pageSize);
        }
        return getMapper().selectByExample(example);


    }





}
