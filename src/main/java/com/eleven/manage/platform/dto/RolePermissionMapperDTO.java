package com.eleven.manage.platform.dto;

import lombok.Data;

import java.util.List;

/**
 * @author ywl
 * @date 2018/05/24
 */
@Data
public class RolePermissionMapperDTO{
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

    private List<Integer> permissions;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public List<Integer> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Integer> permissions) {
        this.permissions = permissions;
    }
}