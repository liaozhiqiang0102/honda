package com.sv.honda.repository;

import com.sv.honda.entity.RoleEntity;
import com.sv.honda.entity.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends BaseRepository<RoleEntity,Integer>   {
    /**
     * 根据角色名查询符合条件的角色(模糊查询)
     * @param roleName
     * @return 符合条件的角色列表
     */
    @Query("from RoleEntity as r where r.roleName like %:roleName%")
    List<RoleEntity> findRolesByName(@Param("roleName") String roleName);

    /**
     * 根据角色名查id符合条件的角色
     * @param roleId
     * @return 符合条件的角色
     */
    @Query("from RoleEntity as r where r.roleId=:roleId")
    RoleEntity findRoleById(@Param("roleId") Integer roleId);
}
