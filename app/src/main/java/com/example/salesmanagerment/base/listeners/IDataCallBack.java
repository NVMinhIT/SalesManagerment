package com.example.salesmanagerment.base.listeners;

/**
 * Là interface lắng nghe xứ lý khi lấy dữ liệu
 *
 *
 * @param <T> là kiểu dữ liệu kết quả trả về
 */
public interface IDataCallBack<T> {

    /**
     * Được gọi khi lấy dữ liệu về thành công
     *
     *
     * @param data là dữ liệu được trả về
     */
    void onDataSuccess(T data);

    /**
     *
     * Mô tả:  được gọi khi việc lấy dữ liệu bị thất bại
     *
     * @param msg là thông điệp muốn trả về để xử lý
     */
    void onDataFailed(String msg);
}
