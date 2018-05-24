package com.eleven.manage.platform.dto;

import lombok.Data;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Data
public class PermissionMenuMapperDTO{
    private Integer id;

    private Integer permissionId;

    private Integer menuId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}