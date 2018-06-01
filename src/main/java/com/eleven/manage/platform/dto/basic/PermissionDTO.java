package com.eleven.manage.platform.dto.basic;

import lombok.Data;
/**
 * @author ywl
 * @date 2018/05/24
 */
@Data
public class PermissionDTO{
    private Integer id;

    private String Name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}