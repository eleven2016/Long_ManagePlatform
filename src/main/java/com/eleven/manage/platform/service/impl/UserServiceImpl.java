package com.eleven.manage.platform.service.impl;

import com.alibaba.fastjson.JSON;
import com.eleven.manage.platform.mybatis.dao.UserDao;
import com.eleven.manage.platform.mybatis.model.UserDO;
import com.eleven.manage.platform.service.UserService;
import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ywl
 * @date 2018/5/16
 **/
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public int addUser(UserDO user) {
        logger.info("新增参数:"+ JSON.toJSONString(user));
        return userDao.insert(user);
    }

    /*
    * 这个方法中用到了我们开头配置依赖的分页插件pagehelper
    * 很简单，只需要在service层传入参数，然后将参数传递给一个插件的一个静态方法即可；
    * pageNum 开始页数
    * pageSize 每页显示的数据条数
    * */
    @Override
    public List<UserDO> findAllUser(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        PageHelper.startPage(pageNum, pageSize);
        UserDO param = new UserDO();
        return userDao.selectByCondition(param);
    }
}
