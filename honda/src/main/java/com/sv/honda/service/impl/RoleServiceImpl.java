package com.sv.honda.service.impl;

import com.sv.honda.entity.RoleEntity;
import com.sv.honda.repository.RoleRepository;
import com.sv.honda.service.RoleService;
import org.apache.commons.collections.IteratorUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleRepository roleRepository;

    /**
     * 根据角色名查询符合条件的角色(模糊查询)
     * @param roleName
     * @return 符合条件的角色列表
     */
    @Override
    public List<RoleEntity> findRolesByName(String roleName) {
        return this.roleRepository.findRolesByName(roleName);
    }

    /**
     * 分页查询所有角色
     * @param pageable 分页需要的条件
     * @return 所有角色列表
     */
    @Override
    public Page<RoleEntity> findAllRole(Pageable pageable) {
        return this.roleRepository.findAll(pageable);
    }

    /**
     * 不分页查询所有角色
     * @return 所有角色列表
     */
    @Override
    public List<RoleEntity> findAllRole() {
        return IteratorUtils.toList(this.roleRepository.findAll().iterator());
    }

    /**
     * 保存角色
     */
    @Override
    public void saveRole(RoleEntity roleEntity) {
        this.roleRepository.save(roleEntity);
    }

    /**
     * 根据角色id查询角色信息
     * @param roleId
     * @return 角色对象
     */
    @Override
    public RoleEntity findRoleById(Integer roleId) {
        return this.roleRepository.findRoleById(roleId);
    }

    /**
     * 根据角色id删除角色信息
     * @param roleId
     */
    @Override
    public void deleteRole(Integer roleId) {
        RoleEntity roleEntity = this.findRoleById(roleId);
        this.roleRepository.delete(roleEntity);
    }

    /**
     * 更新角色信息
     * @param roleEntity
     * @return 角色对象
     */
    @Override
    public RoleEntity updateRole(RoleEntity roleEntity) {
        return this.roleRepository.save(roleEntity);
    }
}
