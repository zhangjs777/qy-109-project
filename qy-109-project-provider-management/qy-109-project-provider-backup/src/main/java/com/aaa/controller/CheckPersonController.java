package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.CheckPerson;
import com.aaa.model.News;
import com.aaa.service.CheckPersonService;
import com.aaa.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: qy-109-project
 * @author: 张梦波
 * @create: 2020-07-18 15:17
 **/
@RestController
/*@RequestMapping("/checkperson")*/
public class CheckPersonController extends BaseController {

    @Autowired
    private CheckPersonService checkPersonService;

    /**
     * @Description: 查询所有信息
     * @Param: []
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/allCheckPaerson")
    public ResultData selectALLCheckPaerson(){
        ResultData resultData=checkPersonService.selectALLCheckPerson();
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
    @PostMapping("/pageCheckPerson")
    public ResultData selectAllCheckPersonByPage(@RequestBody RoleVo roleVo){
        ResultData resultData=checkPersonService.selectALLCheckPersonByge(roleVo);
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
    @PostMapping("/insertCheckPerson")
    public ResultData<News> insertCheckPerson(@RequestBody CheckPerson checkPerson){
        Boolean aBoolean=checkPersonService.addCheckPerson(checkPerson);
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
    @PostMapping("/updateCheckPerson")
    public ResultData<News> updatetCheckPerson(@RequestBody CheckPerson checkPerson){
        Boolean aBoolean=checkPersonService.updateCheckPerson(checkPerson);
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
    @PostMapping("/deleteCheckPerson")
    public ResultData deleteCheckPerson(@RequestParam("id") Long id){
        Boolean aBoolean=checkPersonService.deleteCheckPerson(id);
        if (true == aBoolean){
            return super.operationSuccess();
        }else{
            return super.operationFailed();
        }
    }
}
