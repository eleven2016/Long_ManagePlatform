package com.eleven.manage.platform.dto.basic;

import lombok.Data;

import java.util.List;

/**
 * @author ywl
 * @date 2018/05/24
 */
@Data
public class UserRoleMapperDTO{
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private List<Integer> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public void setRoles(List<Integer> roles) {
        this.roles = roles;
    }
}