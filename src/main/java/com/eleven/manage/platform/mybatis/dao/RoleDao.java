package com.eleven.manage.platform.mybatis.dao;

import com.eleven.manage.platform.mybatis.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Service("roleDao")
public interface RoleDao{
    public int deleteById (java.lang.Integer id);

    public int update (RoleDO param);

    public int insert (RoleDO param);

    public List<RoleDO> selectById(RoleDO param);

    public List<RoleDO> selectByCondition(RoleDO param);

}