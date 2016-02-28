package com.csi0n.zhihudaily.utils.model.response;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by chqss on 2016/2/28 0028.
 */
public class BaseResponse implements Serializable {

    private int status;

    private String errorMsg;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }


    public boolean isSuccess() {
        return status == 0;
    }
}
