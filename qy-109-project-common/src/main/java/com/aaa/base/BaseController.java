package com.aaa.base;

import static com.aaa.status.LoginStatus.*;
import static com.aaa.status.OperationStatus.*;

/**
 * @program: springcloud-zjs-0708-project
 * @description:
 * @author: 张梦波
 * @create: 2020-07-08 16:04
 **/
public class BaseController {
    
    /**
    * @Author: js.zhang
    * @Description: 登陆成功使用系统消息
    * @DateTime: 2020/7/9 17:03
    * @Params: []
    * @Return com.aaa.zjs.base.ResultData
    */
    protected ResultData loginSuccess(){
        ResultData resultData= new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        return resultData;
    }
    /**
    * @Author: js.zhang
    * @Description: 登录成功自定义返回消息
    * @DateTime: 2020/7/9 17:03
    * @Params: [msg]
    * @Return com.aaa.zjs.base.ResultData
    */
    protected  ResultData loginSuccess(String msg){
        ResultData resultData= new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }
    /**
    * @Author: js.zhang
    * @Description: 登录成功 返回数据信息，使用系统消息
    * @DateTime: 2020/7/9 17:04
    * @Params: [data]
    * @Return com.aaa.zjs.base.ResultData
    */
    protected  ResultData loginSuccess(Object data){
        ResultData resultData=new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }
    /**
    * @Author: js.zhang
    * @Description: 登陆成功，返回数据信息，自定义消息
    * @DateTime: 2020/7/9 17:04
    * @Params: [msg, data]
    * @Return com.aaa.zjs.base.ResultData
    */
    protected ResultData loginSuccess(String msg,Object data){
        ResultData resultData=new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }
    /**
    * @Author: js.zhang
    * @Description: 登录失败，使用系统消息
    * @DateTime: 2020/7/8 16:55
    * @Params: []
    * @Return com.aaa.zjs.base.ResultData
    */
    protected  ResultData loginFailed(){
        ResultData resultData=new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        return resultData;
    }
        /**
        * @Author: js.zhang
        * @Description: 登录失败，使用系统消息，详细解释说明
        * @DateTime: 2020/7/9 17:05
        * @Params: [detail]
        * @Return com.aaa.zjs.base.ResultData
        */
    protected  ResultData loginFailed(String detail){
        ResultData resultData=new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        resultData.setDetail(detail);
        return resultData;
    }


        /**
        * @Author: js.zhang
        * @Description: 操作成功，返回系统消息
        * @DateTime: 2020/7/9 17:09
        * @Params: []
        * @Return com.aaa.zjs.base.ResultData
        */
    protected ResultData operationSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(SUCCESS.getCode());
        resultData.setMsg(SUCCESS.getMsg());
        return resultData;
    }

    protected ResultData operationFailed(){
        ResultData resultData=new ResultData();
        resultData.setCode(FAILED.getCode());
        resultData.setMsg(FAILED.getMsg());
        return resultData;
    }
    //TODO 未完善
}
