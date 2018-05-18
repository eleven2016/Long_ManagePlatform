package com.eleven.manage.platform.mybatis.model;

import lombok.Data;
/**
 * @author ywl
 * @date 2018/05/18
 */
@Data
public class UserDO{
    private Integer id;

    private String userName;

    private String account;

    private String password;

    private java.util.Date createTime;

    private java.util.Date modifyTime;

}