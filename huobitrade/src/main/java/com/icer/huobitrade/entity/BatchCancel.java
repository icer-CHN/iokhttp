package com.icer.huobitrade.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by cljlo on 2018/1/21.
 */

public class BatchCancel implements Serializable {

    private List<String> success;
    private List<Failed> failed;

    public List<String> getSuccess() {
        return success;
    }

    public void setSuccess(List<String> success) {
        this.success = success;
    }

    public List<Failed> getFailed() {
        return failed;
    }

    public void setFailed(List<Failed> failed) {
        this.failed = failed;
    }

    @Override
    public String toString() {
        return "BatchCancel{" +
                "success=" + success +
                ", failed=" + failed +
                '}';
    }

    public static class Failed {
        /**
         * err-msg : 记录无效
         * order-id : 2
         * err-code : base-record-invalid
         */

        @SerializedName("err-msg")
        private String errMsg;
        @SerializedName("order-id")
        private String orderId;
        @SerializedName("err-code")
        private String errCode;

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getErrCode() {
            return errCode;
        }

        public void setErrCode(String errCode) {
            this.errCode = errCode;
        }

        @Override
        public String toString() {
            return "Failed{" +
                    "errMsg='" + errMsg + '\'' +
                    ", orderId='" + orderId + '\'' +
                    ", errCode='" + errCode + '\'' +
                    '}';
        }
    }
}
