package com.eleven.manage.platform.mybatis.model;

import lombok.Data;

import java.util.Date;

/**
 * 用户实体类
 * @author ywl
 * @date 2018/5/16
 **/
@Data
public class UserDO {
    private Integer id;

    private String userName;

    private Date createTime;
}
