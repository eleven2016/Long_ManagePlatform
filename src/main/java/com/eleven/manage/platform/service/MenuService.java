package com.eleven.manage.platform.service;

import com.eleven.manage.platform.dto.MenuDTO;

import java.util.List;

/**
 * @author ywl
 * @date 2018/05/24
 **/
public interface MenuService {
    int deleteById(java.lang.Integer id);

    int update(MenuDTO param);

    int insert(MenuDTO param);

    List<MenuDTO> selectById(MenuDTO param);

    List<MenuDTO> selectByCondition(MenuDTO param);
}
