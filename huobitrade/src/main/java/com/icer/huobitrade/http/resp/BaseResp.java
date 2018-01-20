package com.icer.huobitrade.http.resp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by icer-SP4 on 2018/1/20.
 */

public class BaseResp implements Serializable {

    /**
     * 请求处理结果 "ok" , "error"
     */
    private String status;

    @SerializedName("err-code")
    private String errorCode;

    @SerializedName("err-msg")
    private String errorMsg;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public boolean isOk() {
        return "ok".equalsIgnoreCase(status);
    }

    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
