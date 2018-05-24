package com.eleven.manage.platform.service.impl;

import com.eleven.manage.platform.dto.UserDTO;
import com.eleven.manage.platform.mybatis.dao.UserDao;
import com.eleven.manage.platform.mybatis.model.UserDO;
import com.eleven.manage.platform.service.UserService;
import com.eleven.manage.platform.utils.GenerateListResultUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        UserDO user = new UserDO();
        BeanUtils.copyProperties(userDTO,user);
        return userDao.insert(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUser(UserDTO userDTO) {
        UserDO user = new UserDO();
        BeanUtils.copyProperties(userDTO,user);
        return userDao.update(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUser(UserDTO userDTO) {
        return userDao.deleteById(userDTO.getId());
    }

    @Override
    public List<UserDTO> findByPage(UserDTO userDTO, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserDO param = new UserDO();
        BeanUtils.copyProperties(userDTO,param);
        List<UserDO> queryResult = userDao.selectByCondition(param);
        List<UserDTO> result =generateListResultUtil.generate(queryResult,UserDTO.class);
        return result;
    }

    @Override
    public List<UserDTO> findByCondition(UserDTO userDTO) {
        UserDO param = new UserDO();
        BeanUtils.copyProperties(userDTO,param);
        List<UserDO>  queryResult = userDao.selectByCondition(param);
        List<UserDTO> result =generateListResultUtil.generate(queryResult,UserDTO.class);
        return result;
    }

    /**
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    public List<UserDO> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        UserDO param = new UserDO();
        return userDao.selectByCondition(param);
    }
}
