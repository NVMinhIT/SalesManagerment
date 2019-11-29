package com.example.salesmanagerment.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IDataCallBack;
import com.example.salesmanagerment.data.model.entity.ItemOrder;
import com.example.salesmanagerment.data.model.entity.Order;
import com.example.salesmanagerment.data.model.entity.OrderDetail;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

public class CommonFunc {
    private static final String TAG = "CommonFunc";

    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    public CommonFunc(Context context) {
        mContext = context;
    }

    public static String getStringCurrentDateTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.K_DATE_FORMAT, Locale.US);
        return formatter.format(date);
    }

    public static String getStringCurrentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.K_DATE_FORMAT_2, Locale.US);
        return formatter.format(new Date());
    }

    public static String getStringCurrentDateTimeMMddyyyy() {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.K_DATE_FORMAT_4, Locale.US);
        return formatter.format(new Date());
    }

    public static String getCurrentDateTime(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.K_DATE_FORMAT_3, Locale.US);
        return formatter.format(date);

    }


    public static String getCurrentDateBirthday(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.K_DATE_FORMAT_5, Locale.US);
        return formatter.format(date);

    }

    @SuppressLint("SimpleDateFormat")
    public static String getDateServer(String dateTime) {
        Date date1 = null;
        try {
            date1 = new SimpleDateFormat(Constants.K_DATE_FORMAT_4).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat formatter = new SimpleDateFormat(Constants.K_DATE_FORMAT_4, Locale.US);
        return formatter.format(date1);
    }

//    @SuppressLint("SimpleDateFormat")
//    public static String getDateBirthday(String dateTime) {
//        Date date2 = null;
//        try {
//            date2 = new SimpleDateFormat(Constants.K_DATE_FORMAT_5).parse(dateTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        SimpleDateFormat format = new SimpleDateFormat(Constants.K_DATE_FORMAT_5, Locale.US);
//        return format.format(date2);
//    }

    public static void showToastSuccess(int msg) {
        showToastShort(msg, null, ToastEnum.SUCCESS);
    }

    public static void showToastSuccess(String msg) {
        showToastShort(0, msg, ToastEnum.SUCCESS);
    }

    public static void showToastWarning(int msg) {
        showToastShort(msg, null, ToastEnum.WARN);
    }

    public static void showToastWarning(String msg) {
        showToastShort(0, msg, ToastEnum.WARN);
    }

    public static void showToastError(int msg) {
        showToastShort(msg, null, ToastEnum.ERROR);
    }

    public static void showToastError(String msg) {
        showToastShort(0, msg, ToastEnum.ERROR);
    }

    public static void showToastInfo(int msg) {
        showToastShort(msg, null, ToastEnum.INFO);
    }

    public static void showToastInfo(String msg) {
        showToastShort(0, msg, ToastEnum.INFO);
    }

    private static void showToastShort(int message, String msg, ToastEnum type) {
        Toast toast = new Toast(mContext);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP, 0, 50);
        View layout = LayoutInflater.from(mContext).inflate(R.layout.customtoast_layout, null, false);
        TextView content = layout.findViewById(R.id.toast_content);
        LinearLayout linearLayout = layout.findViewById(R.id.ll_toast_layout);
        ImageView img = layout.findViewById(R.id.toast_icon);
        if (msg != null) {
            content.setText(msg);
        } else {
            content.setText(mContext.getString(message));
        }
        Drawable drawable = mContext.getResources().getDrawable(R.drawable.bg_toast);
        switch (type) {
            case SUCCESS:
                drawable.setColorFilter(ContextCompat.getColor(mContext, R.color.color_success), PorterDuff.Mode.SRC);
                img.setImageResource(R.drawable.ic_success_white);
                break;
            case WARN:
                drawable.setColorFilter(ContextCompat.getColor(mContext, R.color.color_warn), PorterDuff.Mode.SRC);
                img.setImageResource(R.drawable.ic_warning_white);
                break;
            case ERROR:
                drawable.setColorFilter(ContextCompat.getColor(mContext, R.color.color_error), PorterDuff.Mode.SRC);
                img.setImageResource(R.drawable.ic_error_white);
                break;
            case INFO:
                drawable.setColorFilter(ContextCompat.getColor(mContext, R.color.color_info), PorterDuff.Mode.SRC);
                img.setImageResource(R.drawable.ic_info_white);
                break;
        }
        linearLayout.setBackground(drawable);
        toast.setView(layout);
        toast.show();
    }

    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap StringToBitMap(String encodedString) {
        try {
            byte[] encodeByte = Base64.decode(encodedString, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public static boolean isNetworkAvailable() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        } catch (Exception ex) {
            Log.e(TAG, "isNetworkAvailable: ", ex);
        }

        return false;
    }

    public static boolean isNullOrEmpty(String s) {
        if (s == null) {
            return true;
        }
        return TextUtils.isEmpty(s);
    }

    public static List<OrderDetail> newItemOrderToOrderDetails(List<ItemOrder> listItemOrder, Order order) {
        List<OrderDetail> list = new ArrayList<>();
        int sortOrder = 0;
        int status;
        String desc = "";

        for (ItemOrder item : listItemOrder) {
            if (CommonFunc.isNullOrEmpty(item.Description)) {
                desc = item.Description;
            }
            status = item.OrderDetailStatus;
            list.add(new OrderDetail.Builder()
                    .setOrderDetailID(UUID.randomUUID().toString())
                    .setOrderID(order.OrderID)
                    .setInventoryItemID(item.ID)
                    .setOrderDetailStatus(status)
                    .setDescription(desc)
                    .setQuantity(item.Quantity)
                    .setSortOrder(sortOrder)
                    .setCookedQuantity(0.0)
                    .setCookingQuantity(0.0)
                    .setServedQuantity(0.0)
                    .build());
            sortOrder++;
            desc = "";
        }
        return list;
    }

    public static void oldItemOrderToOrderDetails(final List<ItemOrder> listItemOrder, final Order order, final IDataCallBack<List<OrderDetail>, Void> callBack) {
        new AsyncTask<Void, Void, List<OrderDetail>>() {
            @Override
            protected List<OrderDetail> doInBackground(Void... voids) {
                List<OrderDetail> list = new ArrayList<>();
                String OrderDetailID;
                Double CookedQuantity = 0.0;
                Double ServedQuantity = 0.0;
                Double CookingQuantity = 0.0;
                int OrderDetailStatus;
                int SortOrder = 0;
                String desc = "";

                for (ItemOrder item : listItemOrder) {
                    if (!CommonFunc.isNullOrEmpty(item.Description)) {
                        desc = item.Description;
                    }
                    OrderDetailStatus = item.OrderDetailStatus;
                    if (!CommonFunc.isNullOrEmpty(item.OrderDetailID)) {
                        OrderDetailID = item.OrderDetailID;
                        if (item.ServedQuantity != null && item.CookedQuantity != null && item.CookingQuantity != null) {
                            CookedQuantity = item.CookedQuantity;
                            ServedQuantity = item.ServedQuantity;
                            CookingQuantity = item.CookingQuantity;
                        }
                        SortOrder = item.SortOrder;

                    } else {
                        OrderDetailID = UUID.randomUUID().toString();
                        SortOrder = SortOrder + 1;
                    }
                    list.add(new OrderDetail.Builder()
                            .setOrderDetailID(OrderDetailID)
                            .setOrderID(order.OrderID)
                            .setInventoryItemID(item.ID)
                            .setOrderDetailStatus(OrderDetailStatus)
                            .setQuantity(item.Quantity)
                            .setSortOrder(SortOrder)
                            .setCookedQuantity(CookedQuantity)
                            .setCookingQuantity(CookingQuantity)
                            .setServedQuantity(ServedQuantity)
                            .setDescription(desc)
                            .build());
                    desc = "";
                }
                return list;
            }

            @Override
            protected void onPostExecute(List<OrderDetail> orderDetails) {
                super.onPostExecute(orderDetails);
                callBack.onDataSuccess(orderDetails);
            }
        }.execute();
    }


    public static OrderDetail ItemOrderToOrderDetail(final ItemOrder itemOrder) {

        return new OrderDetail.Builder()
                .setOrderDetailID(itemOrder.OrderDetailID)
                .setOrderID(itemOrder.OrderID)
                .setInventoryItemID(itemOrder.ID)
                .setOrderDetailStatus(itemOrder.OrderDetailStatus)
                .setQuantity(itemOrder.Quantity)
                .setSortOrder(itemOrder.SortOrder)
                .setCookedQuantity(itemOrder.CookedQuantity)
                .setCookingQuantity(itemOrder.CookingQuantity)
                .setServedQuantity(itemOrder.ServedQuantity)
                .setDescription(itemOrder.Description)
                .build();
    }
}
