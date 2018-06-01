package com.eleven.manage.platform.dto.common;

import lombok.Data;

/**
 * @author ywl
 * @date 2018/5/22
 **/
@Data
public class LoginDTO {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
