package com.eleven.manage.platform.mybatis.model;

import lombok.Data;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Data
public class MenuDO{
    private Integer id;

    private String menuName;

    private Integer pid;

    private Integer level;

    private String menuUrl;

    private java.util.Date modifyTime;

}