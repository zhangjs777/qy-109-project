package com.aaa.controller;

import com.aaa.base.BaseController;
import com.aaa.base.ResultData;
import com.aaa.model.Menu;
import com.aaa.service.IProjectService;
import com.aaa.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @Description:
* @Param:
* @return:
* @Author: ZMB
* @Date: 2020/7/16
*/
@RestController
/*@RequestMapping("/menu")*/
public class MenuController extends BaseController {


    @Autowired
    private IProjectService iProjectService;

    /**
    * @Description: 菜单的模糊查询
    * @Param: [dept]
    * @return: com.aaa.base.ResultData
    * @Author: ZMB
    * @Date: 2020/7/20
    */
    @PostMapping("/selectMenu")
    public ResultData selectMenu(Menu menu){
        return iProjectService.selectMenu(menu);
    }

    /**
    * @Description:  查询所有菜单
    * @Param: []
    * @return: java.util.List<com.aaa.model.Menu>
    * @Author: ZMB
    * @Date: 2020/7/16
    */
    @PostMapping("/getMenus")
    public List<Menu> selectAllMenus(){
        return iProjectService.selectAllMenus();
    }

    /** 
    * @Description: 在菜单管理中新增菜单或者是按钮 
    * @Author: guohang
    * @Date: 2020/6/3 19:02
    * @Param: [menu] 
    * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu> 
    */ 
    @PostMapping("/insertMenuOrButton")
    public ResultData<Menu> insertMenuOrButton(Menu menu){
        return iProjectService.insertMenuOrButton(menu);
    }

    /** 
    * @Description: 在菜单管理中修改菜单或者按钮 
    * @Author: guohang
    * @Date: 2020/6/3 18:45
    * @Param: [menu] 
    * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu> 
    */ 
    @PostMapping("/updateMenuOrButton")
    public ResultData<Menu> updateMenuOrButton(Menu menu){
        return iProjectService.updateMenuOrButton(menu);
    }
    
    /** 
    * @Description: 删除按钮或者菜单 
    * @Author: guohang
    * @Date: 2020/6/3 18:45
    * @Param: [menuId] 
    * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu> 
    */ 
    @PostMapping("/deleteMenuOrButton")
    public ResultData<Menu> deleteMenuOrButton(Long menuId){
        return iProjectService.deleteMenuOrButton(menuId);
    }


}



