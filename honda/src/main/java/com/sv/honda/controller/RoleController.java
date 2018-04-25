package com.sv.honda.controller;

import com.google.gson.Gson;
import com.sv.honda.entity.KendoGridEntiy;
import com.sv.honda.entity.RoleEntity;
import com.sv.honda.entity.UserEntity;
import com.sv.honda.service.RoleService;
import com.sv.honda.util.AjaxJson;
import com.sv.honda.util.DataSourceResult;
import com.sv.honda.util.KendoGridUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 分页查询角色信息列表
     * @param page 当前开始页码（代码中从0开始）
     * @param pageSize 页号
     * @return
     */
    @GetMapping(value="/role/readAllByPage")
    public @ResponseBody
    String findAllRole(@Param("page") String page, @Param("pageSize") String pageSize){
        Gson gson = new Gson();
        DataSourceResult<RoleEntity> RoleEntityDataSourceResult = new DataSourceResult<>();
        PageRequest pageRequest = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(pageSize));
        Page<RoleEntity> roleEntityPage = this.roleService.findAllRole(pageRequest);

        RoleEntityDataSourceResult.setData(roleEntityPage.getContent());
        RoleEntityDataSourceResult.setTotal(roleEntityPage.getTotalPages());
        return gson.toJson(roleEntityPage);
    }

    /**
     * 不分页查询角色信息列表
     * @return
     */
    @PostMapping(value="/role/readAll")
    public @ResponseBody List<RoleEntity> findAllRole(){
        return this.roleService.findAllRole();
    }

    /**
     * 添加角色
     * @param
     * @return
     */
    @PostMapping(value="/role/addRole")
    public AjaxJson addRole(RoleEntity roleEntity){
        this.roleService.saveRole(roleEntity);
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setMsg("添加角色成功！");
        return ajaxJson;
    }

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    @PostMapping(value="/role/deleteRole")
    public AjaxJson deleteRole(String roleId){
        this.roleService.deleteRole(Integer.parseInt(roleId));
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setMsg("删除角色成功！");
        return ajaxJson;
    }

    /**
     * 修改角色
     * @param roleEntity
     * @return
     */
    @PostMapping(value="/role/updateRole")
    public AjaxJson updateRole(RoleEntity roleEntity){
        this.roleService.updateRole(roleEntity);
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setMsg("修改角色成功！");
        return ajaxJson;
    }

    /**
     * 根据角色名查询符合条件的角色(模糊查询)
     * @param roleName
     * @return 符合条件的角色列表
     */
    @PostMapping(value="/findRolesByName/{roleName}")
    public @ResponseBody List<RoleEntity> findRolesByName(@PathVariable("roleName") String roleName){
        List<RoleEntity> RoleEntityList = this.roleService.findRolesByName(roleName);
        return RoleEntityList;
    }

    /**
     * 根据用户id查询角色信息
     * @param roleId
     * @return
     */
    @PostMapping(value="/role/findRoleById/{roleId}")
    public @ResponseBody RoleEntity findRoleById(@PathVariable("roleId") String roleId){
        return this.roleService.findRoleById(Integer.parseInt(roleId));

    }

    /**
     * 跳转到RoleManagement页面
     *
     * @return
     */
    @GetMapping(value = "/roleMgr")
    public ModelAndView turnToRoleMgr() {
        return new ModelAndView("/project/roleManagement");
    }

    /**
     * 跳转到roleTest页面
     *
     * @return
     */
    @GetMapping(value = "/roleMgrTest")
    public ModelAndView turnToRoleMgrTest() {
        return new ModelAndView("/project/roleTest");
    }


    /**
     * 生成角色列表
     * @param response
     * @param fieldValues
     * @param kendoGridEntiy
     * @return
     */
    @RequestMapping(value = "/roleList3", method = RequestMethod.POST)
    public @ResponseBody AjaxJson  roleList3(HttpServletResponse response, @RequestParam(value = "fieldValues[]",required=false) String[] fieldValues,
                                             KendoGridEntiy kendoGridEntiy) {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        response.setCharacterEncoding("utf-8");
        KendoGridUtils kendoGridUtils = new KendoGridUtils();

        RoleEntity role = new RoleEntity();
        kendoGridEntiy.setObject(role);
//        kendoGridEntiy.setUrl("/roleController/");

        StringBuffer sb = kendoGridUtils.kendoGridInit(fieldValues,kendoGridEntiy,"roleList","addRole","deleteRole","roleUpdate");

        AjaxJson ajax = new AjaxJson();
        ajax.setMsg(sb.toString());
        return ajax;
    }
}
