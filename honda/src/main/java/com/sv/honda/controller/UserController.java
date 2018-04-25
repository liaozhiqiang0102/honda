package com.sv.honda.controller;

import com.google.gson.Gson;
import com.sv.honda.entity.KendoGridEntiy;
import com.sv.honda.entity.UserEntity;
import com.sv.honda.service.UserService;
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
import java.util.*;


@RestController
public class UserController {
    @Resource
    private UserService userService;

    /**
     * 分页查询用户信息列表
     * @param page 当前开始页码（代码中从0开始）
     * @param pageSize 页号
     * @return
     */
    @GetMapping(value="/user/readAllByPage")
    public @ResponseBody String findAllUser(@Param("page") String page, @Param("pageSize") String pageSize){
        Gson gson = new Gson();
        DataSourceResult<UserEntity> userEntityDataSourceResult = new DataSourceResult<>();
        PageRequest pageRequest = new PageRequest(Integer.parseInt(page)-1,Integer.parseInt(pageSize));
        Page<UserEntity> userEntityPage = this.userService.findAllUser(pageRequest);
//        List<UserEntity> userEntityList = this.userService.findAllUser(page,pageSize);    //分页查询数据
//        Integer userEntityTotal = this.userService.findUserTotal();       //查询总记录条数

        userEntityDataSourceResult.setData(userEntityPage.getContent());
        userEntityDataSourceResult.setTotal(userEntityPage.getTotalPages());
        return gson.toJson(userEntityPage);
    }

    /**
     * 不分页查询用户信息列表
     * @return
     */
    @PostMapping(value="/user/readAll")
    public @ResponseBody List<UserEntity> findAllUser(){
        return this.userService.findAllUser();
    }

    /**
     * 添加用户
     * @param 
     * @return
     */
    @PostMapping(value="/user/addUser")
    public AjaxJson addUser(UserEntity userEntity){
        this.userService.saveUser(userEntity);
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setMsg("添加用户成功！");
        return ajaxJson;
    }

    /**
     * 删除用户
     * @param userId
     * @return
     */
    @PostMapping(value="/user/deleteUser")
    public AjaxJson deleteUser(String userId){
        this.userService.deleteUser(Integer.parseInt(userId));
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setMsg("删除用户成功！");
        return ajaxJson;
    }

    /**
     * 修改用户
     * @param userEntity
     * @return
     */
    @PostMapping(value="/user/updateUser")
    public AjaxJson updateUser(UserEntity userEntity){
        this.userService.updateUser(userEntity);
        AjaxJson ajaxJson = new AjaxJson();
        ajaxJson.setMsg("修改用户成功！");
        return ajaxJson;
    }

    /**
     * 根据用户名查询符合条件的用户(模糊查询)
     * @param userName
     * @return 符合条件的用户列表
     */
    @PostMapping(value="/findUsersByName/{userName}")
    public @ResponseBody List<UserEntity> findUsersByName(@PathVariable("userName") String userName){
        List<UserEntity> userEntityList = this.userService.findUsersByName(userName);
        return userEntityList;
    }

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return
     */
    @PostMapping(value="/user/findUserById/{userId}")
    public @ResponseBody UserEntity findUserById(@PathVariable("userId") String userId){
        return this.userService.findUserById(Integer.parseInt(userId));

    }


    /**
     * 生成用户列表
     * @param response
     * @param fieldValues
     * @param kendoGridEntiy
     * @return
     */
    @PostMapping(value = "/user/userList3")
    public @ResponseBody AjaxJson  userList3(HttpServletResponse response, @RequestParam(value = "fieldValues[]",required=false) String[] fieldValues,
                                             KendoGridEntiy kendoGridEntiy) {
        response.setContentType("application/json");
        response.setHeader("Cache-Control", "no-store");
        response.setCharacterEncoding("utf-8");
        KendoGridUtils kendoGridUtils = new KendoGridUtils();

        UserEntity user = new UserEntity();
        kendoGridEntiy.setObject(user);
//        kendoGridEntiy.setUrl("/userController/");

        //参数为增删改的方法名
        StringBuffer sb = kendoGridUtils.kendoGridInit(fieldValues,kendoGridEntiy,"./user/readAllByPage","./user/addUser","./user/deleteUser","./user/updateUser");

        AjaxJson ajax = new AjaxJson();
        ajax.setMsg(sb.toString());
        return ajax;
    }

    /**
     * 跳转到userManagement页面
     *
     * @return
     */
    @GetMapping(value = "/userMgr")
    public ModelAndView turnToUserMgr() {
        return new ModelAndView("/project/userManagement");
    }

    /**
     * 跳转到userTest页面
     *
     * @return
     */
    @GetMapping(value = "/userMgrTest")
    public ModelAndView turnToUserMgrTest() {
        return new ModelAndView("/project/userTest");
    }


    /**
     * 查询userEntity类所有列
     *
     * @param
     * @return
     */
    @PostMapping(value = "/user/userFieldsList")
    public @ResponseBody
    List<Map<String,Object>> userFieldsList() {
        UserEntity userEntity = new UserEntity();
        List<Map<String,Object>> userFieldsList = userService.userFieldsList(userEntity);

        return userFieldsList;
    }

}
