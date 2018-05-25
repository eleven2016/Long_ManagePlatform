package com.eleven.manage.platform.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author ywl
 * @date 2018/05/24
 */
@Data
public class RoleDTO{
    private Integer id;

    private String roleName;

    private java.util.Date modifyTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}