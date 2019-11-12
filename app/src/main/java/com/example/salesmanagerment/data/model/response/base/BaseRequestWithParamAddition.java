package com.example.salesmanagerment.data.model.response.base;

/**
 * Lớp đối tượng base response khi lấy từ api về
 *
 * @param <T> - đối tượng response chính trả về
 */
public class BaseRequestWithParamAddition<T, G> {
    private T data = null;
    private G param;

    public T getData() {
        return data;
    }

    public BaseRequestWithParamAddition<T, G> setData(T data) {
        this.data = data;
        return this;
    }

    public G getParam() {
        return param;
    }

    public BaseRequestWithParamAddition<T, G> setParam(G param) {
        this.param = param;
        return this;
    }
}
