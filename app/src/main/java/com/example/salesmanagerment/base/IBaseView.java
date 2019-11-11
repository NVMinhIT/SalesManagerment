package com.example.salesmanagerment.base;

/**
 * Class cơ sở cho các View
 * Created_by Nguyễn Bá Linh on 25/03/2019
 */
public interface IBaseView {
    /**
     * Phương thức nhận 1 thông điệp
     *
     *
     * @param message
     */
    void receiveMessage(int message);

    /**
     * Phương thức hiển thị 1 dialog chờ xử lý tác vụ với 1 thông điệp
     *
     */
    void showLoading();

    /**
     * Phương thức ẩn/đóng dialog đang chờ xử lý khi thực hiện xong tác vụ
     *
     */
    void hideLoading();
}
