package com.eleven.manage.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.eleven.manage.platform.dto.MenuDTO;
import com.eleven.manage.platform.dto.PageResponseDTO;
import com.eleven.manage.platform.mybatis.dao.MenuDao;
import com.eleven.manage.platform.mybatis.model.MenuDO;
import com.eleven.manage.platform.service.MenuService;
import com.eleven.manage.platform.utils.GenerateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 菜单服务
 * @author ywl
 * @date 2018/05/25
 **/
@Service("menuService")
public class MenuServiceImpl implements MenuService {

    private static Logger logger = LoggerFactory.getLogger(MenuServiceImpl.class);

    GenerateUtil<MenuDO, MenuDTO> generateListResultUtil =new GenerateUtil<>();
    @Autowired
    private MenuDao menuDao;

    @Override
    public int deleteById(Integer id) {
        Assert.notNull(id,"ID不能为空!");
        return menuDao.deleteById(id);
    }

    @Override
    public int update(MenuDTO param) {
        Assert.notNull(param,"更新参数不能为空!");
        MenuDO menuDO =new MenuDO();
        BeanUtils.copyProperties(param,menuDO);
        return menuDao.update(menuDO);
    }

    @Override
    public int insert(MenuDTO param) {
        Assert.notNull(param,"新增参数不能为空!");
        MenuDO menuDO =new MenuDO();
        BeanUtils.copyProperties(param,menuDO);
        return menuDao.insert(menuDO);
    }

    @Override
    public MenuDTO selectById(MenuDTO param) {
        Assert.notNull(param,"参数不能为空!");
        Assert.notNull(param.getId(),"参数ID不能为空!");
        MenuDO menuDO =new MenuDO();
        menuDO.setId(param.getId());
        List<MenuDO> queryResult = menuDao.selectById(menuDO);
        MenuDTO result = generateListResultUtil.generateOne(queryResult,MenuDTO.class);
        return result;

    }

    @Override
    public List<MenuDTO> selectByCondition(MenuDTO param) {
        MenuDO menuDO =new MenuDO();
        if( null != param){
            BeanUtils.copyProperties(param,menuDO);
        }
        List<MenuDO> queryResult = menuDao.selectByCondition(menuDO);
        List<MenuDTO> result = generateListResultUtil.generate(queryResult,MenuDTO.class);
        return result;
    }

    @Override
    public PageResponseDTO selectByPage(MenuDTO param, int pageNum, int pageSize) {
        MenuDO menuDO =new MenuDO();
        if( null != param){
            BeanUtils.copyProperties(param,menuDO);
        }
        Page<MenuDO> doPage =PageHelper.startPage(pageNum, pageSize);
        menuDao.selectByCondition(menuDO);
        PageResponseDTO result = generateListResultUtil.generatePage(doPage,MenuDTO.class);
        logger.info("分页查询结果"+ JSON.toJSONString(result));
        return result;
    }
}
