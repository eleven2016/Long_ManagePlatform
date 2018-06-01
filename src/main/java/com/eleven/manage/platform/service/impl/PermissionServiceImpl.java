package com.eleven.manage.platform.service.impl;

import com.eleven.manage.platform.dto.common.PageResponseDTO;
import com.eleven.manage.platform.dto.basic.PermissionDTO;
import com.eleven.manage.platform.dto.basic.PermissionMenuMapperDTO;
import com.eleven.manage.platform.mybatis.dao.PermissionDao;
import com.eleven.manage.platform.mybatis.dao.PermissionMenuMapperDao;
import com.eleven.manage.platform.mybatis.model.PermissionDO;
import com.eleven.manage.platform.mybatis.model.PermissionMenuMapperDO;
import com.eleven.manage.platform.service.PermissionService;
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
 * @author ywl
 * @date 2018/05/25
 **/
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    GenerateUtil<PermissionDO, PermissionDTO>                     permissionGenerateUtil = new GenerateUtil<>();
    GenerateUtil<PermissionMenuMapperDO, PermissionMenuMapperDTO> permissionMenuUtil     = new GenerateUtil<>();
    @Autowired
    private PermissionDao                                         permissionDao;

    @Autowired
    private PermissionMenuMapperDao                               permissionMenuMapperDao;

    @Override
    public int deleteById(java.lang.Integer id) {
        Assert.notNull(id, "ID不能为空!");
        return permissionDao.deleteById(id);
    }

    @Override
    public int update(PermissionDTO param) {
        Assert.notNull(param, "更新参数不能为空!");
        PermissionDO permissionDO = new PermissionDO();
        BeanUtils.copyProperties(param, permissionDO);
        return permissionDao.update(permissionDO);
    }

    @Override
    public int insert(PermissionDTO param) {
        Assert.notNull(param, "新增参数不能为空!");
        PermissionDO permissionDO = new PermissionDO();
        BeanUtils.copyProperties(param, permissionDO);
        return permissionDao.insert(permissionDO);
    }

    @Override
    public PermissionDTO selectById(PermissionDTO param) {
        Assert.notNull(param, "参数不能为空!");
        Assert.notNull(param.getId(), "参数ID不能为空!");
        PermissionDO permissionDO = new PermissionDO();
        permissionDO.setId(param.getId());
        List<PermissionDO> queryResult = permissionDao.selectById(permissionDO);
        PermissionDTO result = permissionGenerateUtil.generateOne(queryResult, PermissionDTO.class);
        return result;
    }

    @Override
    public List<PermissionDTO> selectByCondition(PermissionDTO param) {
        PermissionDO permissionDO = new PermissionDO();
        if (null != param) {
            BeanUtils.copyProperties(param, permissionDO);
        }
        List<PermissionDO> queryResult = permissionDao.selectByCondition(permissionDO);
        List<PermissionDTO> result = permissionGenerateUtil.generate(queryResult, PermissionDTO.class);
        return result;
    }

    @Override
    public PageResponseDTO selectByPage(PermissionDTO param, int pageNum, int pageSize) {
        PermissionDO permissionDO = new PermissionDO();
        if (null != param) {
            BeanUtils.copyProperties(param, permissionDO);
        }
        Page<PermissionDO> doPage = PageHelper.startPage(pageNum, pageSize);
        permissionDao.selectByCondition(permissionDO);
        PageResponseDTO result = permissionGenerateUtil.generatePage(doPage, PermissionDTO.class);
        return result;
    }

    @Override
    public List<PermissionMenuMapperDTO> findMenusByPermission(int id) {
        PermissionMenuMapperDO param = new PermissionMenuMapperDO();
        param.setPermissionId(id);
        List<PermissionMenuMapperDO> permissionMenuMapperDOS = permissionMenuMapperDao.selectByCondition(param);
        return permissionMenuUtil.generate(permissionMenuMapperDOS, PermissionMenuMapperDTO.class);
    }

    @Override
    public void savePerMenuMap(PermissionMenuMapperDTO param) {
        PermissionMenuMapperDO queryParam = new PermissionMenuMapperDO();
        queryParam.setPermissionId(param.getPermissionId());
        List<PermissionMenuMapperDO> existMappers = permissionMenuMapperDao.selectByCondition(queryParam);

        List<PermissionMenuMapperDO> needAddList = new ArrayList<>();
        List<PermissionMenuMapperDO> needDeleteList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(param.getMenus())) {
            if (!CollectionUtils.isEmpty(existMappers)) {
                needAddList = param.getMenus().stream().filter(t -> {
                    Optional<PermissionMenuMapperDO> hasExist = existMappers.stream().filter(item -> t.compareTo(item.getMenuId()) == 0).findFirst();
                    return !hasExist.isPresent();
                }).map(t -> {
                    PermissionMenuMapperDO addParam = new PermissionMenuMapperDO();
                    addParam.setMenuId(t);
                    addParam.setPermissionId(param.getPermissionId());
                    return addParam;
                }).collect(Collectors.toList());
                needDeleteList = existMappers.stream().filter(t -> !param.getMenus().stream().anyMatch(item -> item.compareTo(t.getMenuId()) == 0)).collect(Collectors.toList());
            } else {
                needAddList = param.getMenus().stream().map(t -> {
                    PermissionMenuMapperDO addParam = new PermissionMenuMapperDO();
                    addParam.setMenuId(t);
                    addParam.setPermissionId(param.getPermissionId());
                    return addParam;
                }).collect(Collectors.toList());
            }
        } else {
            needDeleteList = existMappers;
        }
        if (!CollectionUtils.isEmpty(needAddList)) {
            needAddList.forEach(t -> permissionMenuMapperDao.insert(t));
        }
        if (!CollectionUtils.isEmpty(needDeleteList)) {
            needDeleteList.forEach(t -> permissionMenuMapperDao.deleteById(t.getId()));
        }
    }
}
