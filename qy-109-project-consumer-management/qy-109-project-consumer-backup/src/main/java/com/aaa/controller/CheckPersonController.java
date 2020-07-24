package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.CheckPerson;
import com.aaa.model.News;
import com.aaa.service.CheckPersonService;
import com.aaa.service.IProjectService;
import com.aaa.vo.RoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: qy-109-project
 * @author: 张梦波
 * @create: 2020-07-18 15:17
 **/
@RestController
/*@RequestMapping("/checkperson")*/
public class CheckPersonController extends BaseController {

    @Autowired
    private IProjectService iProjectService;

    /**
     * @Description: 查询所有信息
     * @Param: []
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/allCheckPaerson")
    public ResultData selectALLallCheckPaerson(){
        return iProjectService.selectALLCheckPaerson();
    }

    /**
     * @Description: 分页查询信息
     * @Param: [roleVo]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/pageCheckPerson")
    public ResultData selectAllCheckPersonByPage(RoleVo roleVo){
        return iProjectService.selectAllCheckPersonByPage(roleVo);
    }

    /**
     * @Description: 新增信息
     * @Param: [news]
     * @return: com.aaa.base.ResultData<com.aaa.model.News>
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/insertCheckPerson")
    public ResultData<News> insertCheckPerson(CheckPerson checkPerson){
        return iProjectService.insertCheckPerson(checkPerson);
    }

    /**
     * @Description: 修改信息
     * @Param: [news]
     * @return: com.aaa.base.ResultData<com.aaa.model.News>
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/updateCheckPerson")
    public ResultData<News> updatetCheckPerson(CheckPerson checkPerson){
        return iProjectService.updatetCheckPerson(checkPerson);
    }

    /**
     * @Description:  根据主键删除信息
     * @Param: [id]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/deleteCheckPerson")
    public ResultData deleteCheckPerson(Long id){
        return iProjectService.deleteCheckPerson(id);
    }
}
