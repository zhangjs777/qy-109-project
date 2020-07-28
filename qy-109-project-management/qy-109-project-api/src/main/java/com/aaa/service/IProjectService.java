package com.aaa.service;

import com.aaa.base.ResultData;
import com.aaa.model.*;
import com.aaa.vo.RoleVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Company
 * @Author
 * @Date Create in 2020/7/15 9:30
 * @Description
 **/
@FeignClient(value = "sys-interface")
public interface IProjectService {

    /**
     * @author
     * @description
     *      执行登录操作
     * @param [user]
     * @date 2020/7/15
     * @return com.aaa.lee.base.ResultData
     * @throws
    **/
    @PostMapping("/doLogin")
    ResultData doLogin(@RequestBody User user);

    /**
     * @author
     * @description
     *      新增日志
     * @param [loginLog]
     * @date 2020/7/15
     * @return java.lang.Integer
     * @throws
    **/
            @PostMapping("/addLoginLog")
   Integer addLoginLog(@RequestBody LoginLog loginLog);
    /**
     * @Author: js.zhang
     * @Description: 查询全部用户 测试用
     * @DateTime: 2020/7/17 20:05
     * @Params: []
     * @Return com.aaa.base.ResultData
     */
    @RequestMapping("/selectUser")
    ResultData selectUser();
    /**
     * @Author: js.zhang
     * @Description:分页 条件 查询
     * @DateTime: 2020/7/16 21:45
     * @Params: [map]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/selectAllUser")
    ResultData selectAllUser(@RequestBody User user);


    /**
     * @Author: js.zhang
     * @Description: 通过id查询user具体信息 部门 管理员
     * @DateTime: 2020/7/22 21:35
     * @Params: [id]
     * @Return com.aaa.base.ResultData
     */
    @GetMapping("/selectUserById")
    ResultData selectUserById(@RequestParam("id") Long id);

    /**
     * @Author: js.zhang
     * @Description: 模糊查询
     * @DateTime: 2020/7/20 18:37
     * @Params: [user]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/selectUserAll")
   ResultData selectUserAll(@RequestBody User user);

    /**
     * @Author: js.zhang
     * @Description: 新增
     * @DateTime: 2020/7/16 19:34
     * @Params: [map]
     * @Return com.aaa.base.ResultData
     */
    @RequestMapping("/addUser")
    ResultData addUser(@RequestBody Map map);

    /**
     * @Author: js.zhang
     * @Description: 修改
     * @DateTime: 2020/7/16 19:35
     * @Params: [map]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/updateUser")
    ResultData updateUser(@RequestBody Map map);

    /**
     * @Author: js.zhang
     * @Description: 批量删除
     * @DateTime: 2020/7/16 19:35
     * @Params: [map, ids]
     * @Return com.aaa.base.ResultData
     */
    @RequestMapping("/deleteUserById")
    ResultData updateUserStatus(@RequestParam("ids[]") Integer[] ids);


    /**
     * @Author: js.zhang
     * @Description: 查询黑白名单 0 黑 1白
     * @DateTime: 2020/7/22 11:39
     * @Params: [blankAndWirte]
     * @Return com.aaa.base.ResultData
     */
    @RequestMapping("/bwUnit")
    public ResultData bwUnit(@RequestParam("blank") String blank, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);




    /**
     * @Author: js.zhang
     * @Description: 修改mappingUnit
     * @DateTime: 2020/7/17 15:50
     * @Params: [map]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/updateUnit")
    ResultData updateUnit(@RequestBody Map map);



    /**
     * @Author: js.zhang
     * @Description:  通过id查询单位具体信息
     * @DateTime: 2020/7/20 8:37
     * @Params: [mappingUnit]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/selectUnitById")
    ResultData selectUnitById(@RequestBody MappingUnit mappingUnit);

    /**
     * @Author: js.zhang
     * @Description: 通过unitName 查询所有的 单位信息
     * @DateTime: 2020/7/17 9:20
     * @Params: [map]
     * @Return com.aaa.base.ResultData
     */
    @RequestMapping("/selctMappingUnit")
    public  ResultData selectMappingUnit(@RequestBody MappingUnit mappingUnit);

    /**
     * @Author: js.zhang
     * @Description: 通过refId查询对应审核信息  审核表
     * @DateTime: 2020/7/17 10:47
     * @Params: [refId]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/selectByRefId")
   ResultData selectByRefId(@RequestBody Audit audit);


    /**
     * @Author: js.zhang
     * @Description: 测绘单位查询，单位名称模糊查询，单位地域和单位资质准确查询
     * @DateTime: 2020/7/17 15:30
     * @Params: [mappingUnit]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/unitSelect")
     ResultData unitSelect(@RequestBody MappingUnit mappingUnit);


    /**
     * @Author: js.zhang
     * @Description: 通过id查询法人个人信息
     * @DateTime: 2020/7/18 10:23
     * @Params: [userId]
     * @Return com.aaa.base.ResultData
     */
    @GetMapping("/selectPrincipalById")
    ResultData selectPrincipalByUserId(@RequestParam("id") Long id);

    /**
     * @Author: js.zhang
     * @Description: 通过userId查询法人信息列表
     * @DateTime: 2020/7/18 11:44
     * @Params: [principal, pageNo, pageSize]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/selectPrincipalByUserId")
   ResultData selectPrincipalByUserId(@RequestBody Principal principal);

    /**
     * @Author: js.zhang
     * @Description: 新增法人信息
     * @DateTime: 2020/7/21 8:38
     * @Params: [principal]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/addPrincipal")
    ResultData addPrincipal(@RequestBody Principal principal);


    /**
     * @Author: js.zhang
     * @Description: 修改法人
     * @DateTime: 2020/7/22 9:10
     * @Params: [principal]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/updatePrincipal")
    ResultData updatePrincipal(@RequestBody Principal principal);


    /**
     * @Author: js.zhang
     * @Description: 删除法人
     * @DateTime: 2020/7/22 9:13
     * @Params: [principal]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/deletePrincipal")
     ResultData   updatePrinicepal(@RequestBody Principal principal);


    /**
     * @Author: js.zhang
     * @Description: 通过id查询技术人员个人信息
     * @DateTime: 2020/7/18 15:19
     * @Params: [id]
     * @Return com.aaa.base.ResultData
     */
    @GetMapping("/selectTechById")
     ResultData selectTechById(@RequestParam("id") Long id);

    /**
     * @Author: js.zhang
     * @Description: 通过userId查询技术人员列表
     * @DateTime: 2020/7/18 14:34
     * @Params: [technicist, pageNo, pageSize]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/selectTechByUserid")
    public ResultData selectTechByUserid( @RequestBody Technicist technicist,@RequestParam("pageNo") Integer pageNo,@RequestParam("pageSize") Integer pageSize);


     /**
      * @Author: js.zhang
      * @Description: 修改技术人员信息
      * @DateTime: 2020/7/20 11:22
      * @Params: [technicist]
      * @Return com.aaa.base.ResultData
      */
    @PostMapping("/updateTechnicist")
    ResultData updateTechnicist(@RequestBody Technicist technicist);

    /**
     * @Author: js.zhang
     * @Description: 新增技术人员信息
     * @DateTime: 2020/7/20 11:24
     * @Params: [technicist]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("addTechnicist")
    ResultData addTechnicist(@RequestBody Technicist technicist);


    /**
     * @Author: js.zhang
     * @Description: 删除技术人员信息
     * @DateTime: 2020/7/20 11:45
     * @Params: [technicist]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/deleteTechnicist")
    ResultData deleteTechnicist(@RequestBody Technicist technicist);


    /**
     * @Author: js.zhang
     * @Description: 通过refBizId
     * @DateTime: 2020/7/20 21:25
     * @Params: [resource]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/selectByRefBizId")
    ResultData selectByRefBizId(Resource resource);



    /**
     * @Author: js.zhang
     * @Description: 通过unitID查询后分页查询
     * @DateTime: 2020/7/18 8:51
     * @Params: [hashMap]
     * @Return com.aaa.base.ResultData
     */

    @PostMapping("/selectByUnitId")
    ResultData selectByUnitId(@RequestBody HashMap hashMap);



    /**
     * @Author: js.zhang
     * @Description: 修改单位的分数
     * @DateTime: 2020/7/18 9:37
     * @Params: [map]
     * @Return com.aaa.base.ResultData
     */
    @PostMapping("/addScore")
     ResultData addScore(@RequestBody Score score, @RequestParam("id") Long id);







    /**
     * @Author:xfc
     * @Description:
     *          查询数据字典数据
     * @Date:2020/7/16
     * @param pageNo:
     * @param pageSize:
     * @return: com.aaa.base.ResultData
     *
     **/
    @PostMapping("/getAllDict")
    ResultData getAllDict(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author:xfc
     * @Description:
     *          添加字典数据
     * @Date:2020/7/16
     * @param dictionary:
     * @return: java.lang.Boolean
     *
     **/
    @PostMapping("/addDict")
    Boolean addDict(@RequestBody Dictionary dictionary);

    /**
     * @Author:xfc
     * @Description:
     *          删除字典数据
     * @Date:2020/7/16
     * @param dictionary:
     * @return: java.lang.Boolean
     *
     **/
    @PostMapping("/delDict")
    Boolean delDict(@RequestBody Dictionary dictionary);


    /**
    * @Author:xfc
    * @Description:
     *     更新字典数据
    * @Date: 2020/7/23 21:05
    * @param dictionary:
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/updateDict")
    Boolean updateDict(@RequestBody Dictionary dictionary);

    /**
     * @Author:xfc
     * @Description:
     *    查询所有测绘项目信息
     * @Date: 2020/7/17 19:24
     * @param :
     * @return: com.aaa.base.ResultData
     *
     **/
    @PostMapping("/getAllMappingProject")
    ResultData getAllMappingProject(@RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

    /**
     * @Author:xfc
     * @Description:
     *      通过名字查询项目信息
     * @Date: 2020/7/17 19:27
     * @param name:
     * @return: com.aaa.base.ResultData
     *
     **/
    @PostMapping("/getMappingProjectByName")
    ResultData getMappingProjectByName(@RequestParam("name") String name);


    /**
     * @Author:xfc
     * @Description:
     *          通过id 查询测绘信息
     * @Date: 2020/7/17 19:29
     * @param id:
     * @return: com.aaa.base.ResultData
     *
     **/
    @PostMapping("/getMappingProjectById")
    ResultData getMappingProjectById(@RequestParam("id") String id);



    /**
     * @Author:xfc
     * @Description:
     *      首页条件 查询MappingProject信息
     * @Date: 2020/7/22 11:38
     * @param mappingProject:
     * @return: com.aaa.base.ResultData
     **/
    @RequestMapping("/selectAllMappingProject")
     ResultData selectAllMappingProject(@RequestBody MappingProject mappingProject);

    /**
    * @Author:xfc
    * @Description:
     *    输入参数查询信息
    * @Date: 2020/7/28 10:35
    * @param mappingProject:
    * @return: com.aaa.base.ResultData
    *
    **/
   @RequestMapping("/select")
    ResultData select(@RequestBody MappingProject mappingProject);


    /**
    * @Author:xfc
    * @Description:
     *      根据单位id 查找设备信息
    * @Date: 2020/7/20 8:52
    * @param equipment:
     * @param pageNo:
     * @param pageSize:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/getEquipmentByUnitId")
    ResultData getEquipmentByUnitId(@RequestBody Equipment equipment, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);


    /**
    * @Author:xfc
    * @Description:
     *         查询全部设备信息
    * @Date: 2020/7/20 9:56
    * @param map:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/selectAllEquipment")
    ResultData selectAllEquipment(@RequestBody HashMap map);


    /**
    * @Author:xfc
    * @Description:
     *       根据设备id 查找设备信息
    * @Date: 2020/7/20 9:57
    * @param map:
    * @return: com.aaa.base.ResultData
    *
    **/
    @PostMapping("/getEquipmentById")
    ResultData getEquipmentById(@RequestBody HashMap map);


    /**
    * @Author:xfc
    * @Description:
     *          添加设备信息
    * @Date: 2020/7/20 10:01
    * @param equipment:
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/addEquipment")
    Boolean addEquipment(@RequestBody Equipment equipment);

    /**
    * @Author:xfc
    * @Description:
     *       更新设备信息
    * @Date: 2020/7/20 10:01
    * @param equipment:
    * @return: java.lang.Boolean
    *
    **/
    @PostMapping("/updateEquipment")
    Boolean updateEquipment(@RequestBody Equipment equipment);



    /**
    * @Author:xfc
    * @Description:
     *      根据id 删除设备信息
    * @Date: 2020/7/20 10:04
    **/
    @PostMapping("/deleteEquipment")
    Boolean deleteEquipment(@RequestParam("id") Long id);


    /**
    * @Author:xfc
    * @Description:
     *       查询 全部特殊人才信息
    * @Date: 2020/7/23 18:52
    **/
    @PostMapping("/getAllSpecialPost")
   ResultData getAllSpecialPost(@RequestBody SpecialPost specialPost, @RequestParam("pageNo") Integer pageNo, @RequestParam("pageSize") Integer pageSize);

   /**
    * 添加特殊人才信息
    **/

    @PostMapping("/addSpecialPost")
   Boolean addSpecialPost(@RequestBody SpecialPost specialPost);


    /**
     * 更新特殊人才信息
     **/
    @PostMapping("/updateSpecialPost")
     Boolean updateSpecialPost(@RequestBody SpecialPost specialPost);


    /**
     * 根据id 删除特殊人才信息
     **/
    @PostMapping("/deleteSpecialPost")
    Boolean deleteSpecialPost(@RequestParam("id") Long id);





    //------------抽查人员信息表
    /**
     * @Description: 查询所有信息
     * @Param: []
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/allCheckPaerson")
    ResultData selectALLCheckPaerson();

    /**
     * @Description: 分页查询信息
     * @Param: [roleVo]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/pageCheckPerson")
    ResultData selectAllCheckPersonByPage(@RequestBody RoleVo roleVo);

    /**
     * @Description: 新增信息
     * @Param: [news]
     * @return: com.aaa.base.ResultData<com.aaa.model.News>
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/insertCheckPerson")
    ResultData<News> insertCheckPerson(@RequestBody CheckPerson checkPerson);

    /**
     * @Description: 修改信息
     * @Param: [news]
     * @return: com.aaa.base.ResultData<com.aaa.model.News>
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/updateCheckPerson")
    ResultData<News> updatetCheckPerson(@RequestBody CheckPerson checkPerson);

    /**
     * @Description:  根据主键删除信息
     * @Param: [id]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/deleteCheckPerson")
    ResultData deleteCheckPerson(@RequestParam("id") Long id);

    //------------部门表
    /**
     * @Description:  部门模糊管理
     * @Param: [dept]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/20
     */
    @PostMapping("/selectDept")
    ResultData selectDeptAll(@RequestBody Dept dept);

    /**
     * @Description: 查询所有部门
     * @Param: []
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/17
     */
    @PostMapping("/allDepts")
    ResultData selectAllDept();

    /**
     * @Description:  简单的分页查询
     * @Param: [roleVo]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/17
     */
    @PostMapping("/pageDepts")
    ResultData selectAllDeptByPage(@RequestBody RoleVo roleVo);

    /**
     * @Description:  新增部门信息
     * @Param: [dept]
     * @return: com.aaa.base.ResultData<com.aaa.model.Dept>
     * @Author: ZMB
     * @Date: 2020/7/17
     */
    @PostMapping("/insertDept")
    ResultData<Dept> insertDept(@RequestBody Dept dept);

    /**
     * @Description:  修改部门信息
     * @Param: [dept]
     * @return: com.aaa.base.ResultData<com.aaa.model.Dept>
     * @Author: ZMB
     * @Date: 2020/7/17
     */
    @PostMapping("/updateDept")
    ResultData<Dept> updateDept(@RequestBody Dept dept);

    /**
     * @Description:  删除部门信息
     * @Param: [dept]
     * @return: com.aaa.base.ResultData<com.aaa.model.Dept>
     * @Author: ZMB
     * @Date: 2020/7/17
     */
    @PostMapping("/deleteDept")
    ResultData<Dept> deleteDept(@RequestParam("deptId") Long deptId);

    //----------菜单表
    /**
     * @Description: 菜单的模糊查询
     * @Param: [dept]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/20
     */
    @PostMapping("/selectMenu")
    ResultData selectMenu(@RequestBody Menu menu);

    /**
     * @Description:  查询所有菜单
     * @Param: []
     * @return: java.util.List<com.aaa.model.Menu>
     * @Author: ZMB
     * @Date: 2020/7/16
     */
    @PostMapping("/getMenus")
    List<Menu> selectAllMenus();

    /**
     * @Description: 在菜单管理中新增菜单或者是按钮
     * @Author: guohang
     * @Date: 2020/6/3 19:02
     * @Param: [menu]
     * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu>
     */
    @PostMapping("/insertMenuOrButton")
    ResultData<Menu> insertMenuOrButton(@RequestBody Menu menu);

    /**
     * @Description: 在菜单管理中修改菜单或者按钮
     * @Author: guohang
     * @Date: 2020/6/3 18:45
     * @Param: [menu]
     * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu>
     */
    @PostMapping("/updateMenuOrButton")
    ResultData<Menu> updateMenuOrButton(@RequestBody Menu menu);

    /**
     * @Description: 删除按钮或者菜单
     * @Author: guohang
     * @Date: 2020/6/3 18:45
     * @Param: [menuId]
     * @return: com.aaa.qy108.base.ResultData<com.aaa.qy108.model.Menu>
     */
    @PostMapping("/deleteMenuOrButton")
    ResultData<Menu> deleteMenuOrButton(@RequestParam("menuId") Long menuId);

    //------信息公开表（新闻）
    /**
     * @Description: 信息公开的模糊查询
     * @Param: [news]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/20
     */
    @PostMapping("/selectNews")
    ResultData selectNewsAll(@RequestBody News news);

    /**
     * @Description: 查询所有信息
     * @Param: []
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/allNews")
    ResultData selectallnews();

    /**
     * @Description: 分页查询信息
     * @Param: [roleVo]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/pageNews")
    ResultData selectAllNewsByPage(@RequestBody RoleVo roleVo);

    /**
     * @Description: 新增信息
     * @Param: [news]
     * @return: com.aaa.base.ResultData<com.aaa.model.News>
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/insertNews")
    ResultData<News> insertNews(@RequestBody News news);

    /**
     * @Description: 修改信息
     * @Param: [news]
     * @return: com.aaa.base.ResultData<com.aaa.model.News>
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/updateNews")
    ResultData<News> updatetNews(@RequestBody News news);

    /**
     * @Description:  根据主键删除信息
     * @Param: [id]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/18
     */
    @PostMapping("/deleteNews")
    ResultData deleteNews(@RequestParam("id") long id);

    //------角色表
    /**
     * @Description: 模糊查询角色
     * @Param: [role]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/20
     */
    @PostMapping("/selectRole")
    ResultData selectRoleAll(@RequestBody Role role);

    /**
     * @Description:  查询所有的角色
     * @Param: []
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/17
     */
    @PostMapping("/allRoles")
    ResultData selectallrole();

    /**
     * @Description:  简单的分页查询
     * @Param: [roleVo]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/17
     */
    @PostMapping("/pageRoles")
    ResultData selectAllRoleByPage(@RequestBody RoleVo roleVo);

    /**
     * @Description:  删除角色
     * @Param: [roleId]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/16
     */
    @PostMapping("/deleteRole")
    ResultData deleteRole(@RequestParam("roleId") Long roleId);

    /**
     * @Description:  新增角色以及批量新增权限
     * @Param: [roleVo]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/16
     */
    @PostMapping("/insertRole")
    ResultData insertRole(@RequestBody RoleVo roleVo);

    /**
     * @Description:  修改角色及其权限
     * @Param: [roleVo]
     * @return: com.aaa.base.ResultData
     * @Author: ZMB
     * @Date: 2020/7/16
     */
    @PostMapping("/updateRole")
    ResultData updateRole(@RequestBody RoleVo roleVo);

    }







