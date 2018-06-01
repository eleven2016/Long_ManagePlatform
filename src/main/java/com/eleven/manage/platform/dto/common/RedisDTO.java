package com.eleven.manage.platform.dto.common;

/**
 * @author ywl
 * @date 2018/6/1
 **/
public class RedisDTO {

    private String key;

    private String value;

    private Long limitTime;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getLimitTime() {
        return limitTime;
    }

    public void setLimitTime(Long limitTime) {
        this.limitTime = limitTime;
    }
}
