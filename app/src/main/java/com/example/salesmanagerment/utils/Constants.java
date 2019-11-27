package com.example.salesmanagerment.utils;

public final class Constants {
    public static final int TYPE_ADD = 1;
    public static final int TYPE_EDIT = 2;


    public static final int REQUEST_CODE = 1;
    public static final String TOKEN = "TOKEN";
    public static final String ARG_UNIT = "ARG_UNIT";
    public static final String ARG_NAME = "ARG_NAME";
    public static final String ARG_INVENTORY_ITEM_CATEGORY = "ARG_INVENTORY_ITEM_CATEGORY";
    public static final String ARG_TYPE_DELETE = "ARG_TYPE_DELETE";
    public static final String NAME_USER = "NAME";
    public static final String PASSWORD_USER = "PASS";
    public static final String EXTRA_CLASS = "dialogFragmentAddCustomer";
    public static final String NUMBER_PERSON = "NUMBER_PERSON";
    public static final String NUMBER_TABLE = "NUMBER_TABLE";
    public static final String TABLE_MAPPING = "TABLE_MAPPING";
    public static final String ACTION_ADD_ORDER = "ACTION_ADD_ORDER";

    public static final int FOOD_TYPE = 0;
    public static final int DRINK_TYPE = 1;

    public static final String FOOD = "Món ăn";
    public static final String DRINK = "Đồ uống";
    public static final int REQUEST_CODE_CAMERA = 123;
    public static final int REQUEST_CODE_FOLDER = 456;
    public static final String FOOD_1 = "Món khai vị";
    public static final String FOOD_2 = "Món chính";
    public static final String FOOD_3 = "Món tráng miệng";
    public static final String FOOD_4 = "Món khác";

    public static final int TYPE_FOOD_1 = 1;
    public static final int TYPE_FOOD_2 = 2;
    public static final int TYPE_FOOD_3 = 3;
    public static final int TYPE_FOOD_4 = 0;

    public static final String K_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String K_DATE_FORMAT_2 = "MM-dd-yyyy HH:mm:ss";
    public static final String K_DATE_FORMAT_3 = "dd/MM/yyyy HH:mm:ss";
    public static final String K_DATE_FORMAT_4 = "MM/dd/yyyy";
    public static final String K_DATE_FORMAT_5 = "dd/MM/yyyy";


    public static final String EXTRAS_INVENTORY_ITEM_LIST = "EXTRAS_INVENTORY_ITEM_LIST";

    //trạng thái bàn
    public static final int TABLE_EMPTY = 0; //đang trống
    public static final int TABLE_ARE_SERVING = 1; //đang phục vụ
    public static final int TABLE_RESERVE = 2; //đã đặt trước

    public static final String EXTRAS_ORDER_ENTITY = "EXTRAS_ORDER_ENTITY";
    public static final String EXTRAS_INVOICE_ENTITY = "EXTRAS_INVOICE_ENTITY";
    public static final String EXTRAS_INVOICE_ENTITY_lIST = "EXTRAS_ORDER_ENTITY_lIST";
    public static final String EXTRAS_TABLE_NAME = "EXTRAS_TABLE_NAME";
    public static final String EXTRAS_NUM_OF_PEOPLE = "EXTRAS_NUM_OF_PEOPLE";
    public static final String SUM_MONEY = "SUM_MONEY";

    //Order status Trạng thái Order (0- đang phục vụ, 1- chờ thanh toán, 2- đã thanh toán, 3- hủy )
    public static final int ORDER_SERVING = 0; //đang phục vụ
    public static final int ORDER_WAIT_FOR_PAY = 1; // CHỜ THANH TOÁN
    public static final int ORDER_PAYED = 2; //ĐÃ THANH TOÁN
    public static final int ORDER_CANCEL = 3; //HỦY

    //Trạng thái chi tiết Order: 0-chưa phục vụ,
    // 1-đã gửi bếp, 2-đang chế biến, 3-đã chế biến, 4-đã phục vụ, 5-hủy món
    public static final int ORDER_DETAIL_NOTHING = 0; //CHƯA PHỤC VỤ
    public static final int ORDER_DETAIL_SENT_KITCHEN = 1; //ĐÃ GỬI BẾP
    public static final int ORDER_DETAIL_PROCESSING = 2; //ĐANG CHẾ BIẾN
    public static final int ORDER_DETAIL_PROCESSED = 3; //ĐÃ CHẾ BIẾN
    public static final int ORDER_DETAIL_SERVED = 4; //ĐÃ PHỤC VỤ
    public static final int ORDER_DETAIL_CANCEL = 5; //HỦY MÓN


    public static final String EXTRAS_TYPE_SCREEN = "EXTRAS_TYPE_SCREEN";
}
