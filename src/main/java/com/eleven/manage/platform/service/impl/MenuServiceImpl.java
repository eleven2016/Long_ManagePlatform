package com.eleven.manage.platform.service.impl;

import com.eleven.manage.platform.dto.MenuDTO;
import com.eleven.manage.platform.mybatis.dao.MenuDao;
import com.eleven.manage.platform.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ywl
 * @date 2018/05/24
 **/
@Service("menuService")
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuDao menuDao;

    @Override
    public int deleteById(java.lang.Integer id) {
        return 0;
    }

    @Override
    public int update(MenuDTO param) {
        return 0;
    }

    @Override
    public int insert(MenuDTO param) {
        return 0;
    }

    @Override
    public List<MenuDTO> selectById(MenuDTO param) {
        return null;
    }

    @Override
    public List<MenuDTO> selectByCondition(MenuDTO param) {
        return null;
    }
}
