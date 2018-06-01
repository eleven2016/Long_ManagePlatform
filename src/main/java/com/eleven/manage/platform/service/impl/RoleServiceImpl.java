package com.eleven.manage.platform.service.impl;

import com.eleven.manage.platform.dto.common.PageResponseDTO;
import com.eleven.manage.platform.dto.basic.RoleDTO;
import com.eleven.manage.platform.dto.basic.RolePermissionMapperDTO;
import com.eleven.manage.platform.mybatis.dao.RoleDao;
import com.eleven.manage.platform.mybatis.dao.RolePermissionMapperDao;
import com.eleven.manage.platform.mybatis.model.RoleDO;
import com.eleven.manage.platform.mybatis.model.RolePermissionMapperDO;
import com.eleven.manage.platform.service.RoleService;
import com.eleven.manage.platform.utils.GenerateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 角色服务
 * @author ywl
 * @date 2018/05/25
 **/
@Service("roleService")
public class RoleServiceImpl implements RoleService {

    GenerateUtil<RoleDO, RoleDTO>                                 generateListResultUtil     = new GenerateUtil<>();
    GenerateUtil<RolePermissionMapperDO, RolePermissionMapperDTO> rolePermissionGenerateUtil = new GenerateUtil<>();

    @Autowired
    private RoleDao                                                         roleDao;

    @Autowired
    private RolePermissionMapperDao                                         rolePermissionMapperDao;

    @Override
    public int deleteById(java.lang.Integer id) {
        Assert.notNull(id, "ID不能为空!");
        return roleDao.deleteById(id);
    }

    @Override
    public int update(RoleDTO param) {
        Assert.notNull(param, "更新参数不能为空!");
        RoleDO roleDO = new RoleDO();
        BeanUtils.copyProperties(param, roleDO);
        return roleDao.update(roleDO);
    }

    @Override
    public int insert(RoleDTO param) {
        Assert.notNull(param, "新增参数不能为空!");
        RoleDO roleDO = new RoleDO();
        BeanUtils.copyProperties(param, roleDO);
        return roleDao.insert(roleDO);
    }

    @Override
    public RoleDTO selectById(RoleDTO param) {
        Assert.notNull(param, "参数不能为空!");
        Assert.notNull(param.getId(), "参数ID不能为空!");
        RoleDO menuDO = new RoleDO();
        menuDO.setId(param.getId());
        List<RoleDO> queryResult = roleDao.selectById(menuDO);
        RoleDTO result = generateListResultUtil.generateOne(queryResult, RoleDTO.class);
        return result;
    }

    @Override
    public List<RoleDTO> selectByCondition(RoleDTO param) {
        RoleDO menuDO = new RoleDO();
        if(null != param){
            BeanUtils.copyProperties(param, menuDO);
        }
        List<RoleDO> queryResult = roleDao.selectByCondition(menuDO);
        List<RoleDTO> result = generateListResultUtil.generate(queryResult, RoleDTO.class);
        return result;
    }

    @Override
    public PageResponseDTO selectByPage(RoleDTO param, int pageNum, int pageSize) {
        RoleDO roleDO = new RoleDO();
        if (null != param) {
            BeanUtils.copyProperties(param, roleDO);
        }
        Page<RoleDO> doPage = PageHelper.startPage(pageNum, pageSize);
        roleDao.selectByCondition(roleDO);
        PageResponseDTO result = generateListResultUtil.generatePage(doPage, RoleDTO.class);
        return result;
    }

    @Override
    public List<RolePermissionMapperDTO> findPermissionsByRoleId(int roleId) {
        RolePermissionMapperDO param =new RolePermissionMapperDO();
        param.setRoleId(roleId);
        List<RolePermissionMapperDO> rolePermissionMapperDOS = rolePermissionMapperDao.selectByCondition(param);
        return rolePermissionGenerateUtil.generate(rolePermissionMapperDOS,RolePermissionMapperDTO.class);
    }

    @Override
    public void saveRolePerMap(RolePermissionMapperDTO param) {
        RolePermissionMapperDO queryParam = new RolePermissionMapperDO();
        queryParam.setRoleId(param.getRoleId());
        List<RolePermissionMapperDO> existMappers = rolePermissionMapperDao.selectByCondition(queryParam);

        List<RolePermissionMapperDO> needAddList = new ArrayList<>();
        List<RolePermissionMapperDO> needDeleteList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(param.getPermissions())){
            if(!CollectionUtils.isEmpty(existMappers)){
                needAddList = param.getPermissions().stream().filter(t->{
                    Optional<RolePermissionMapperDO> hasExist = existMappers.stream().filter(item->t.compareTo(item.getPermissionId())==0).findFirst();
                    return !hasExist.isPresent();
                }).map(t->{
                    RolePermissionMapperDO addParam =new RolePermissionMapperDO();
                    addParam.setPermissionId(t);
                    addParam.setRoleId(param.getRoleId());
                    return addParam;
                }).collect(Collectors.toList());
                needDeleteList = existMappers.stream().filter(t->!param.getPermissions().stream().anyMatch(item->item.compareTo(t.getPermissionId())==0)).collect(Collectors.toList());
            }else {
                needAddList = param.getPermissions().stream().map(t->{
                    RolePermissionMapperDO addParam =new RolePermissionMapperDO();
                    addParam.setPermissionId(t);
                    addParam.setRoleId(param.getRoleId());
                    return addParam;
                }).collect(Collectors.toList());
            }
        }else{
            needDeleteList = existMappers;
        }
        if(!CollectionUtils.isEmpty(needAddList)){
            needAddList.forEach(t->rolePermissionMapperDao.insert(t));
        }
        if(!CollectionUtils.isEmpty(needDeleteList)){
            needDeleteList.forEach(t-> rolePermissionMapperDao.deleteById(t.getId()));
        }
    }
}
