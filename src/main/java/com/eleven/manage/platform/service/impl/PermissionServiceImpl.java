package com.eleven.manage.platform.service.impl;

import com.eleven.manage.platform.dto.PageResponseDTO;
import com.eleven.manage.platform.dto.PermissionDTO;
import com.eleven.manage.platform.mybatis.dao.PermissionDao;
import com.eleven.manage.platform.mybatis.model.PermissionDO;
import com.eleven.manage.platform.service.PermissionService;
import com.eleven.manage.platform.utils.GenerateListResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @author ywl
 * @date 2018/05/25
 **/
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

    GenerateListResultUtil<PermissionDO, PermissionDTO> generateListResultUtil =new GenerateListResultUtil<>();
    @Autowired
    private PermissionDao permissionDao;

    @Override
    public int deleteById(java.lang.Integer id) {
        Assert.notNull(id,"ID不能为空!");
        return permissionDao.deleteById(id);
    }

    @Override
    public int update(PermissionDTO param) {
        Assert.notNull(param,"更新参数不能为空!");
        PermissionDO permissionDO =new PermissionDO();
        BeanUtils.copyProperties(param,permissionDO);
        return permissionDao.update(permissionDO);
    }

    @Override
    public int insert(PermissionDTO param) {
        Assert.notNull(param,"新增参数不能为空!");
        PermissionDO permissionDO =new PermissionDO();
        BeanUtils.copyProperties(param,permissionDO);
        return permissionDao.insert(permissionDO);
    }

    @Override
    public PermissionDTO selectById(PermissionDTO param) {
        Assert.notNull(param,"参数不能为空!");
        Assert.notNull(param.getId(),"参数ID不能为空!");
        PermissionDO permissionDO =new PermissionDO();
        permissionDO.setId(param.getId());
        List<PermissionDO> queryResult = permissionDao.selectById(permissionDO);
        PermissionDTO result = generateListResultUtil.generateOne(queryResult,PermissionDTO.class);
        return result;
    }

    @Override
    public List<PermissionDTO> selectByCondition(PermissionDTO param) {
        Assert.notNull(param,"参数不能为空!");
        PermissionDO permissionDO =new PermissionDO();
        BeanUtils.copyProperties(param,permissionDO);
        List<PermissionDO> queryResult = permissionDao.selectByCondition(permissionDO);
        List<PermissionDTO> result = generateListResultUtil.generate(queryResult,PermissionDTO.class);
        return result;
    }

    @Override
    public PageResponseDTO selectByPage(PermissionDTO param, int pageNum, int pageSize) {
        PermissionDO permissionDO =new PermissionDO();
        if( null != param){
            BeanUtils.copyProperties(param,permissionDO);
        }
        Page<PermissionDO> doPage = PageHelper.startPage(pageNum, pageSize);
        permissionDao.selectByCondition(permissionDO);
        PageResponseDTO result = generateListResultUtil.generatePage(doPage,PermissionDTO.class);
        return result;
    }
}
