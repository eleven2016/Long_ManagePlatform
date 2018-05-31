package com.eleven.manage.platform.mybatis.dao;

import com.eleven.manage.platform.mybatis.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Service("permissionMenuMapperDao")
public interface PermissionMenuMapperDao{
    int deleteById (java.lang.Integer id);

    int update (PermissionMenuMapperDO param);

    int insert (PermissionMenuMapperDO param);

    List<PermissionMenuMapperDO> selectById(PermissionMenuMapperDO param);

    List<PermissionMenuMapperDO> selectByCondition(PermissionMenuMapperDO param);

}