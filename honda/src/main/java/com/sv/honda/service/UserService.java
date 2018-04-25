package com.sv.honda.service;

import com.sv.honda.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 根据用户名查询符合条件的用户(模糊查询)
     * @param userName
     * @return 符合条件的用户列表
     */
    List<UserEntity> findUsersByName(String userName);

    /**
     * 分页查询所有用户
     * @param pageable 分页需要的条件
     * @return 所有用户列表
     */
    Page<UserEntity> findAllUser(Pageable pageable);

    /**
     * 不分页查询所有用户
     * @return 所有用户列表
     */
    List<UserEntity> findAllUser();

    /**
     * 保存用户
     */
    void saveUser(UserEntity userEntity);

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return 用户对象
     */
    UserEntity findUserById(Integer userId);

    /**
     * 根据用户id删除用户信息
     * @param userId
     */
    void deleteUser(Integer userId);

    /**
     * 更新用户信息
     * @param userEntity
     * @return 用户对象
     */
    UserEntity updateUser(UserEntity userEntity);

    /**
     * 分页查询所有用户
     * @param page 当前页数
     * @param pageSize 页面容量(每页的记录数
     * @return 所有用户列表
     */
    List<UserEntity> findAllUser(String page,String pageSize);

    /**
     * 查询用户表总记录条数
     * @return
     */
    Integer findUserTotal();

    /**
     * 查询所有要显示的列
     * @param userEntity
     * @return
     */
    List<Map<String,Object>> userFieldsList(UserEntity userEntity);
}
