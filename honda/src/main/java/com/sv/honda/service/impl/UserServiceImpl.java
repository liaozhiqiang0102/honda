package com.sv.honda.service.impl;

import com.sv.honda.entity.UserEntity;
import com.sv.honda.repository.UserRepository;
import com.sv.honda.service.UserService;
import com.sv.honda.util.KendoGridUtils;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    /**
     * 根据用户名查询符合条件的用户(模糊查询)
     * @param userName
     * @return 符合条件的用户列表
     */
    @Override
    public List<UserEntity> findUsersByName(String userName) {
        return this.userRepository.findUsersByName(userName);
    }

    /**
     * 分页查询所有用户
     * @param pageable 分页需要的条件
     * @return 所有用户列表
     */
    @Override
    public Page<UserEntity> findAllUser(Pageable pageable) {
        return this.userRepository.findAll(pageable);
    }

    /**
     * 不分页查询所有用户
     * @return 所有用户列表
     */
    @Override
    public List<UserEntity> findAllUser() {
        return IteratorUtils.toList(this.userRepository.findAll().iterator());
    }

    /**
     * 根据用户id查询用户信息
     * @param userId
     * @return 用户对象
     */
    @Override
    public UserEntity findUserById(Integer userId) {
        return this.userRepository.findUserById(userId);
    }

    /**
     * 保存用户
     */
    @Override
    @Transactional
    public void saveUser(UserEntity userEntity) {
        this.userRepository.save(userEntity);

    }

    /**
     * 根据用户id删除用户信息
     * @param userId
     */
    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        UserEntity userEntity = findUserById(userId);
        this.userRepository.delete(userEntity);
    }

    /**
     * 更新用户信息
     * @param userEntity
     * @return 用户对象
     */
    @Override
    @Transactional
    public UserEntity updateUser(UserEntity userEntity) {
        return this.userRepository.save(userEntity);
    }


    /**
     * 分页查询所有用户
     * @param page 当前页数
     * @param pageSize 页面容量(每页的记录数
     * @return 所有用户列表
     */
    @Override
    public List<UserEntity> findAllUser(String page,String pageSize) {
        //偏移量(mysql limit分页需要offset，pageSize)
        int offset = (Integer.parseInt(page)-1)*Integer.parseInt(pageSize);
        int pageSize2 = Integer.parseInt(pageSize);
        return this.userRepository.findAllUsersByPage(offset,pageSize2);
    }

    /**
     * 查询用户表总记录条数
     * @return
     */
    @Override
    public Integer findUserTotal() {
        return this.userRepository.findUserTotal();
    }


    /**
     * 查询所有要显示的列
     * @param userEntity
     * @return
     */
    @Override
    public List<Map<String, Object>> userFieldsList(UserEntity userEntity) {
        List<Map<String,Object>> strList = KendoGridUtils.getKey(userEntity);
        return strList;
    }
}
