package com.aaa.base;

import com.aaa.utils.BaseUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
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





        /**
         * @Description: 查询List数据
         * @Author: guohang
         * @Date: 2020/5/13 10:30
         * @Param: [map]
         * @return: com.aaa.qy108.base.ResultData
         */
        public ResultData selectListData(@RequestBody Map map){
                // TODO: 2020/5/13 总感觉 queryListByFields中的fields字段有问题，没有查询条件
                T instance = getBaseService().newInstance(map);
                try {
                        List<T> tList = getBaseService().selectList(instance);
                        return  super.operationSuccess(tList);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return  super.operationFailed();
        }

        /**
         * @Description: 不带条件的分页查询
         * @Author: guohang
         * @Date: 2020/5/13 1:17
         * @Param: [map]
         * @return: com.aaa.qy108.base.ResultData
         */
        public ResultData selectAllByPage(@RequestBody Map map){
                Integer pageNo = BaseUtil.transToInt(map.get("pageNo"));
                Integer pageSize = BaseUtil.transToInt(map.get("pageSize"));
                Object t = map.get("t");
                try {
                        PageInfo<T> tPageInfo = getBaseService().selectListByPage((T) t, pageNo, pageSize);
                        return super.operationSuccess(tPageInfo);
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return super.operationFailed();
        }


        /**
         * @Description: 查询一条数据
         * @Author: guohang
         * @Date: 2020/5/13 0:48
         * @Param: [map]
         * @return: com.aaa.qy108.base.ResultData
         */
        public ResultData selectOne(@RequestBody Map map){
                T instance = getBaseService().newInstance(map);
                try {
                        T t = getBaseService().selectOne(instance);
                        if (null != t && !"".equals(t)){
                                return super.operationSuccess(t);
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return super.operationFailed();
        }



        /**
         * @Description: 修改数据
         * @Author: guohang
         * @Date: 2020/5/13 0:37
         * @Param: [map]
         * @return: com.aaa.qy108.base.ResultData
         */
        public ResultData update(@RequestBody Map map){
                T instance = getBaseService().newInstance(map);
                try {
                        Integer result = getBaseService().update(instance);
                        if (result > 0){
                                return super.operationSuccess();
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return super.operationFailed();
        }



        /**
         * @Description: 防止数据不安全，所以不能直接在controller某个方法中直接接收HttpServletRequest对象,必须要从本地当前线程中获取对象
         * @Author: guohang
         * @Date: 2020/5/12 22:52
         * @Param: []
         * @return: javax.servlet.http.HttpServletRequest
         */
        public HttpServletRequest getServletRequest(){
                RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
                ServletRequestAttributes servletRequestAttributes;
                if (requestAttributes instanceof  ServletRequestAttributes){
                        servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
                        return servletRequestAttributes.getRequest();
                }
                return null;
        }

        /**
         * @Description: 使用本地线程中的HttpServletRequest对象获取客户端的session对象，如果不存在则重新创建一个
         * @Author: guohang
         * @Date: 2020/5/12 22:55
         * @Param: []
         * @return: javax.servlet.http.HttpSession
         */
        public HttpSession getSession(){
                return getServletRequest().getSession();
        }


        /**
         * @Description: 获取当前客户端的session对象，如果不存在，则直接返回为null
         * @Author: guohang
         * @Date: 2020/5/12 22:56
         * @Param: []
         * @return: javax.servlet.http.HttpSession
         */
        public HttpSession getExistSession(){
                return getServletRequest().getSession(false);
        }


}
