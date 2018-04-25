package com.sv.honda.service;

import com.sv.honda.entity.RoleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RoleService {
    /**
     * 根据角色名查询符合条件的角色(模糊查询)
     * @param roleName
     * @return 符合条件的角色列表
     */
    List<RoleEntity> findRolesByName(String roleName);

    /**
     * 分页查询所有角色
     * @param pageable 分页需要的条件
     * @return 所有角色列表
     */
    Page<RoleEntity> findAllRole(Pageable pageable);

    /**
     * 不分页查询所有角色
     * @return 所有角色列表
     */
    List<RoleEntity> findAllRole();

    /**
     * 保存角色
     */
    void saveRole(RoleEntity roleEntity);

    /**
     * 根据角色id查询角色信息
     * @param roleId
     * @return 角色对象
     */
    RoleEntity findRoleById(Integer roleId);

    /**
     * 根据角色id删除角色信息
     * @param roleId
     */
    void deleteRole(Integer roleId);

    /**
     * 更新角色信息
     * @param roleEntity
     * @return 角色对象
     */
    RoleEntity updateRole(RoleEntity roleEntity);
}
