package com.example.salesmanagerment.data.repository.order;

import com.example.salesmanagerment.data.api.ApiService;
import com.example.salesmanagerment.data.api.ServiceGenerator;
import com.example.salesmanagerment.data.model.entity.InventoryItem;
import com.example.salesmanagerment.data.model.entity.Order;
import com.example.salesmanagerment.data.model.entity.OrderDetail;
import com.example.salesmanagerment.utils.CacheManager;

import java.util.HashMap;
import java.util.List;

public class OrderDataSource {
    private static final String TAG = "OrderDataSource";
    private static OrderDataSource instance;
    private String token;
    private HashMap<String, List<OrderDetail>> mListOrderDetail;

    private List<InventoryItem> mInventoryItemList;


    public static OrderDataSource getInstance() {
        if (instance == null) {
            instance = new OrderDataSource();
        }
        return instance;
    }

    private ApiService apiService;

    private OrderDataSource() {
        apiService = ServiceGenerator.createService(ApiService.class);
        token = CacheManager.cacheManager.getToken();
    }
}
