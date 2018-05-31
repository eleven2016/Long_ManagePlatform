package com.eleven.manage.platform.mybatis.dao;

import com.eleven.manage.platform.mybatis.model.*;
import org.springframework.stereotype.Service;
import java.util.List;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Service("menuDao")
public interface MenuDao{
    int deleteById (java.lang.Integer id);

    int update (MenuDO param);

    int insert (MenuDO param);

    List<MenuDO> selectById(MenuDO param);

    List<MenuDO> selectByCondition(MenuDO param);

    List<Integer> selectByUser(java.lang.Integer userId);

}