package com.eleven.manage.platform.dto.common;

/**
 * 统一返回格式
 * @author ywl
 * @date 2018/5/22
 **/
public class ResponseDTO {
    private Boolean success;
    private String  errorCode;
    private String  errorMessage;
    private Object  data;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public ResponseDTO(Boolean success, String errorCode, String errorMessage, Object data) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public ResponseDTO(Boolean success, String errorCode, String errorMessage) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ResponseDTO(Boolean success) {
        this.success = success;
    }
    public ResponseDTO() {
        this.success = false;
    }
}
