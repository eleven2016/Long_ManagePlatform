package com.eleven.manage.platform.mybatis.dao;

import com.eleven.manage.platform.mybatis.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Service("rolePermissionMapperDao")
public interface RolePermissionMapperDao{
    int deleteById (java.lang.Integer id);

    int update (RolePermissionMapperDO param);

    int insert (RolePermissionMapperDO param);

    List<RolePermissionMapperDO> selectById(RolePermissionMapperDO param);

    List<RolePermissionMapperDO> selectByCondition(RolePermissionMapperDO param);

}