package com.example.salesmanagerment.data.model.response.base;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Lớp đối tượng base response khi lấy từ api về
 *
 * @param <T> - đối tượng response chính trả về
 */
public class BaseResponse<T> {
    @SerializedName("Success")
    @Expose
    private Boolean success;
    @SerializedName("ErrorCode")
    @Expose
    private String errorCode;
    @SerializedName("Errors")
    @Expose
    private List<Object> errors = null;
    @SerializedName("Data")
    @Expose
    private T data = null;
    @SerializedName("CustomData")
    @Expose
    private Object customData;

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

    public List<Object> getErrors() {
        return errors;
    }

    public void setErrors(List<Object> errors) {
        this.errors = errors;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Object getCustomData() {
        return customData;
    }

    public void setCustomData(Object customData) {
        this.customData = customData;
    }
}
