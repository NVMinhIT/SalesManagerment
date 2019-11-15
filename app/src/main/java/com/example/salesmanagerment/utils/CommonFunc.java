package com.example.salesmanagerment.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.example.salesmanagerment.R;
import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    public static Date getCurrentDateTime(String dateTime) {
        try {
            return new SimpleDateFormat(Constants.K_DATE_FORMAT).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

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
        } catch (Exception ex)  {
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
}
