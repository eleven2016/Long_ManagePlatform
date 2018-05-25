package com.eleven.manage.platform.service.impl;

import com.eleven.manage.platform.dto.RoleDTO;
import com.eleven.manage.platform.mybatis.dao.RoleDao;
import com.eleven.manage.platform.mybatis.model.RoleDO;
import com.eleven.manage.platform.service.RoleService;
import com.eleven.manage.platform.utils.GenerateListResultUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 角色服务
 * @author ywl
 * @date 2018/05/25
 **/
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    GenerateListResultUtil<RoleDO, RoleDTO> generateListResultUtil =new GenerateListResultUtil<>();

    @Autowired
    private RoleDao roleDao;

    @Override
    public int deleteById(java.lang.Integer id) {
        Assert.notNull(id,"ID不能为空!");
        return roleDao.deleteById(id);
    }

    @Override
    public int update(RoleDTO param) {
        Assert.notNull(param,"更新参数不能为空!");
        RoleDO roleDO =new RoleDO();
        BeanUtils.copyProperties(param,roleDO);
        return roleDao.update(roleDO);
    }

    @Override
    public int insert(RoleDTO param) {
        Assert.notNull(param,"新增参数不能为空!");
        RoleDO roleDO =new RoleDO();
        BeanUtils.copyProperties(param,roleDO);
        return roleDao.insert(roleDO);
    }

    @Override
    public RoleDTO selectById(RoleDTO param) {
        Assert.notNull(param,"参数不能为空!");
        Assert.notNull(param.getId(),"参数ID不能为空!");
        RoleDO menuDO =new RoleDO();
        menuDO.setId(param.getId());
        List<RoleDO> queryResult = roleDao.selectById(menuDO);
        RoleDTO result = generateListResultUtil.generateOne(queryResult,RoleDTO.class);
        return result;
    }

    @Override
    public List<RoleDTO> selectByCondition(RoleDTO param) {
        Assert.notNull(param,"参数不能为空!");
        RoleDO menuDO =new RoleDO();
        BeanUtils.copyProperties(param,menuDO);
        List<RoleDO> queryResult = roleDao.selectByCondition(menuDO);
        List<RoleDTO> result = generateListResultUtil.generate(queryResult,RoleDTO.class);
        return result;
    }
}
