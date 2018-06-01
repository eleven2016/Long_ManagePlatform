package com.eleven.manage.platform.service.impl;

import com.eleven.manage.platform.dto.common.PageResponseDTO;
import com.eleven.manage.platform.dto.basic.UserDTO;
import com.eleven.manage.platform.dto.basic.UserRoleMapperDTO;
import com.eleven.manage.platform.mybatis.dao.UserDao;
import com.eleven.manage.platform.mybatis.dao.UserRoleMapperDao;
import com.eleven.manage.platform.mybatis.model.UserDO;
import com.eleven.manage.platform.mybatis.model.UserRoleMapperDO;
import com.eleven.manage.platform.service.UserService;
import com.eleven.manage.platform.utils.EncryptUtil;
import com.eleven.manage.platform.utils.GenerateUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户服务
 * @author ywl
 * @date 2018/5/16
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    GenerateUtil<UserDO, UserDTO>                     userGenerateUtil     = new GenerateUtil<>();
    GenerateUtil<UserRoleMapperDO, UserRoleMapperDTO> userRoleGenerateUtil = new GenerateUtil<>();
    @Autowired
    private UserDao                                   userDao;

    @Autowired
    private UserRoleMapperDao                         userRoleMapperDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(UserDTO userDTO) {
        Assert.notNull(userDTO, "新增参数不能为空!");
        UserDO user = new UserDO();
        // 密码加密
        if(StringUtils.isNotBlank(userDTO.getPassword())){
            userDTO.setPassword(EncryptUtil.md5Encode(userDTO.getPassword()));
        }
        BeanUtils.copyProperties(userDTO, user);
        return userDao.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(UserDTO userDTO) {
        Assert.notNull(userDTO, "更新参数不能为空!");
        UserDO user = new UserDO();
        if(StringUtils.isNotBlank(userDTO.getPassword())){
            user.setId(userDTO.getId());
            List<UserDO> existUser = userDao.selectById(user);
            Assert.notEmpty(existUser,"非法用户更新");
            Assert.isTrue(existUser.size() == 1,"非法用户更新");
            if(!existUser.get(0).getPassword().equals(userDTO.getPassword())){
                userDTO.setPassword(EncryptUtil.md5Encode(userDTO.getPassword()));
            }
        }
        BeanUtils.copyProperties(userDTO, user);
        return userDao.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUser(int id) {
        return userDao.deleteById(id);
    }

    @Override
    public PageResponseDTO findByPage(UserDTO userDTO, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserDO param = new UserDO();
        if (null != param) {
            BeanUtils.copyProperties(userDTO, param);
        }
        Page<UserDO> doPage = PageHelper.startPage(pageNum, pageSize);
        userDao.selectByCondition(param);
        PageResponseDTO result = userGenerateUtil.generatePage(doPage, UserDTO.class);
        return result;
    }

    @Override
    public List<UserDTO> findByCondition(UserDTO userDTO) {
        UserDO param = new UserDO();
        if (null != param) {
            BeanUtils.copyProperties(userDTO, param);
        }
        List<UserDO> queryResult = userDao.selectByCondition(param);
        List<UserDTO> result = userGenerateUtil.generate(queryResult, UserDTO.class);
        return result;
    }

    @Override
    public UserDTO findById(int id) {
        UserDO param = new UserDO();
        param.setId(id);
        List<UserDO> queryResult = userDao.selectById(param);
        UserDTO result = userGenerateUtil.generateOne(queryResult, UserDTO.class);
        return result;
    }

    @Override
    public List<UserRoleMapperDTO> findRolesByUserId(int userId) {
        UserRoleMapperDO param = new UserRoleMapperDO();
        param.setUserId(userId);
        List<UserRoleMapperDO> userRoleMapperDOS = userRoleMapperDao.selectByCondition(param);
        return userRoleGenerateUtil.generate(userRoleMapperDOS, UserRoleMapperDTO.class);
    }

    @Override
    public void saveUserRoleMap(UserRoleMapperDTO param) {
        UserRoleMapperDO queryParam = new UserRoleMapperDO();
        queryParam.setUserId(param.getUserId());
        List<UserRoleMapperDO> existMappers = userRoleMapperDao.selectByCondition(queryParam);

        List<UserRoleMapperDO> needAddList = new ArrayList<>();
        List<UserRoleMapperDO> needDeleteList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(param.getRoles())){
            if(!CollectionUtils.isEmpty(existMappers)){
                needAddList = param.getRoles().stream().filter(t->{
                    Optional<UserRoleMapperDO> hasExist = existMappers.stream().filter(item->t.compareTo(item.getRoleId())==0).findFirst();
                    return !hasExist.isPresent();
                }).map(t->{
                    UserRoleMapperDO addParam =new UserRoleMapperDO();
                    addParam.setRoleId(t);
                    addParam.setUserId(param.getUserId());
                    return addParam;
                }).collect(Collectors.toList());
                needDeleteList = existMappers.stream().filter(t->!param.getRoles().stream().anyMatch(item->item.compareTo(t.getRoleId())==0)).collect(Collectors.toList());
            }else {
                needAddList = param.getRoles().stream().map(t->{
                    UserRoleMapperDO addParam =new UserRoleMapperDO();
                    addParam.setRoleId(t);
                    addParam.setUserId(param.getUserId());
                    return addParam;
                }).collect(Collectors.toList());
            }
        }else{
            needDeleteList = existMappers;
        }
        if(!CollectionUtils.isEmpty(needAddList)){
            needAddList.forEach(t->userRoleMapperDao.insert(t));
        }
        if(!CollectionUtils.isEmpty(needDeleteList)){
            needDeleteList.forEach(t-> userRoleMapperDao.deleteById(t.getId()));
        }
    }
}
