package com.eleven.manage.platform.mybatis.model;

import lombok.Data;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Data
public class RolePermissionMapperDO{
    private Integer id;

    private Integer roleId;

    private Integer permissionId;

}