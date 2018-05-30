package com.eleven.manage.platform.service.impl;

import com.eleven.manage.platform.dto.PageResponseDTO;
import com.eleven.manage.platform.dto.UserDTO;
import com.eleven.manage.platform.mybatis.dao.UserDao;
import com.eleven.manage.platform.mybatis.model.UserDO;
import com.eleven.manage.platform.service.UserService;
import com.eleven.manage.platform.utils.GenerateListResultUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 用户服务
 * @author ywl
 * @date 2018/5/16
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    GenerateListResultUtil<UserDO, UserDTO> generateListResultUtil =new GenerateListResultUtil<>();
    @Autowired
    private UserDao userDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addUser(UserDTO userDTO) {
        Assert.notNull(userDTO,"新增参数不能为空!");
        UserDO user = new UserDO();
        BeanUtils.copyProperties(userDTO,user);
        return userDao.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(UserDTO userDTO) {
        Assert.notNull(userDTO,"更新参数不能为空!");
        UserDO user = new UserDO();
        BeanUtils.copyProperties(userDTO,user);
        return userDao.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUser(int  id) {
        return userDao.deleteById(id);
    }

    @Override
    public PageResponseDTO findByPage(UserDTO userDTO, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserDO param = new UserDO();
        if( null != param){
            BeanUtils.copyProperties(userDTO,param);
        }
        Page<UserDO> doPage =PageHelper.startPage(pageNum, pageSize);
        userDao.selectByCondition(param);
        PageResponseDTO result = generateListResultUtil.generatePage(doPage,UserDTO.class);
        return result;
    }

    @Override
    public List<UserDTO> findByCondition(UserDTO userDTO) {
        UserDO param = new UserDO();
        if( null != param){
            BeanUtils.copyProperties(userDTO,param);
        }
        List<UserDO>  queryResult = userDao.selectByCondition(param);
        List<UserDTO> result =generateListResultUtil.generate(queryResult,UserDTO.class);
        return result;
    }

    @Override
    public UserDTO findById(int id) {
        UserDO param =new UserDO();
        param.setId(id);
        List<UserDO> queryResult = userDao.selectById(param);
        UserDTO result = generateListResultUtil.generateOne(queryResult,UserDTO.class);
        return result;
    }
}
