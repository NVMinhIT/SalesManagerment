package com.example.salesmanagerment.base.listeners;

/**
 * Là listener chịu trách nhiệm cho sự kiện long click cho View
 *
 *
 * @param <T> là kiểu dữ liệu được truyền đi khi sự kiện long click xảy ra
 */
public interface IOnItemLongClickListener<T> {
    /**
     * Phương thức được sử dụng khi sự kiện click của View được kích hoạt
     *
     *
     * @param data là tham số được truyền vào khi View được click
     */
    void onItemClick(T data);
}
