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
    /**
     * 响应生成时间点，单位：毫秒
     */
    private long ts;
    /**
     * 数据所属的 channel，格式： market.$symbol.detail.merged
     */
    private String ch;

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

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
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

    public boolean isLatestThan(BaseResp resp) {
        return ts > resp.ts;
    }

    @Override
    public String toString() {
        return "{" +
                "status='" + status + '\'' +
                ", ts=" + ts +
                ", ch='" + ch + '\'' +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
