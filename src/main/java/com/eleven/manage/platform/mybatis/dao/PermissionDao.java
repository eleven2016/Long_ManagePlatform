package com.eleven.manage.platform.mybatis.dao;

import com.eleven.manage.platform.mybatis.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Service("permissionDao")
public interface PermissionDao{
    int deleteById (java.lang.Integer id);

    int update (PermissionDO param);

    int insert (PermissionDO param);

    List<PermissionDO> selectById(PermissionDO param);

    List<PermissionDO> selectByCondition(PermissionDO param);

}