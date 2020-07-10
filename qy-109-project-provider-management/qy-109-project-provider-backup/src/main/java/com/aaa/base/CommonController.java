package com.aaa.base;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Map;

/**
 * @program: qy-109-project
 * @description:
 * @author: 张竞赛
 * @create: 2020-07-09 17:47
 **/
public abstract class CommonController<T> extends BaseController {

        public abstract BaseService<T> getBaseService();

        protected void  beforeAdd(Map map){

        }
        protected void  afterAdd(Map map){

                }
        /**
        * @Author: js.zhang
        * @Description:     通用的新增方法
         *      因为咱们目前市面上所有的公司实现的全是异步了
         *    也就是说前端向后端所传递的数据都是json格式
         *   之前在controller的方法中接收固定的实体类，是因为你知道前端给你传递的类型就是这个实体类
         *      但是既然要做通用，前端所传递的类型就不会固定了--->所以使用Map类型来统一接收
        * @DateTime: 2020/7/9 18:19
        * @Params: [map]
        * @Return com.aaa.zjs.base.ResultData
        */
        public ResultData add(@RequestBody Map map){
                // 因为根据咱们的封装规则，在service中是需要传递泛型的，就意味着service需要接收固定的实体类
                // 但是controller是一个Map类型
                beforeAdd(map);
                //map转实体类
                T instance = getBaseService().newInstance(map);
                //2通用service
                Integer addResult =getBaseService().add(instance);
                if (addResult>0){
                        afterAdd(map);
                        return super.operationSuccess();
                }
                return super.operationFailed();
        }

        /**
        * @Author: js.zhang
        * @Description: 删除操作
        * @DateTime: 2020/7/9 18:35
        * @Params: [map]
        * @Return com.aaa.zjs.base.ResultData
        */
        public  ResultData delete(@RequestBody Map map){
                T instace= getBaseService().newInstance(map);
                Integer deleteResult=getBaseService().delete(instace);
                if (deleteResult>0){
                        return super.operationSuccess();
                }else {
                        return super.operationFailed();
                }
        }
        /**
        * @Author: js.zhang
        * @Description: 批量删除
        * @DateTime: 2020/7/9 18:39
        * @Params: [ids]
        * @Return com.aaa.zjs.base.ResultData
        */
        public ResultData batchDelete(@RequestParam("ids[]") Integer [] ids){
                Integer deleteResult = getBaseService().deleteByIds(Arrays.asList(ids));
                if (deleteResult>0){
                        return super.operationSuccess();
                }
                return super.operationFailed();
        }
}
