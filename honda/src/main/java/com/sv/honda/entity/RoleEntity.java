package com.sv.honda.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "role", schema = "honda_om", catalog = "")
public class RoleEntity {
    private int roleId;
    private String roleName;
    private Boolean roleState;

    @Id
    @Column(name = "role_id")
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "role_name")
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "role_state")
    public Boolean getRoleState() {
        return roleState;
    }

    public void setRoleState(Boolean roleState) {
        this.roleState = roleState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return roleId == that.roleId &&
                Objects.equals(roleName, that.roleName) &&
                Objects.equals(roleState, that.roleState);
    }

    @Override
    public int hashCode() {

        return Objects.hash(roleId, roleName, roleState);
    }
}
