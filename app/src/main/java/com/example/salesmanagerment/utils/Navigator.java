package com.example.salesmanagerment.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.salesmanagerment.R;
/**
 * Lớp điều hướng cho ứng dụng
 */
public class Navigator {

    @NonNull
    private AppCompatActivity mActivity;
    @NonNull
    private Fragment mFragment;

    /**
     * Phương thức khởi tạo lớp Navigator với tham số truyền vào là 1 activity
     *
     * @param activity - activity
     */
    public Navigator(@NonNull Activity activity) {
        mActivity = (AppCompatActivity) activity;
    }

    /**
     * Phương thức khởi tạo lớp Navigator với tham số truyền vào là 1 fragment
     *
     * @param fragment - fragment
     */
    public Navigator(@NonNull Fragment fragment) {
        mFragment = fragment;
        mActivity = (AppCompatActivity) fragment.getActivity();
    }

    /**
     * Phương thức khởi chạy 1 activity thông qua 1 intent
     *
     * @param intent - intent
     */
    public void startActivity(@NonNull Intent intent) {
        mActivity.startActivity(intent);
        setActivityTransactionAnimation(ActivityTransition.START);
    }

    /**
     * Phương thức khởi chạy 1 activity thông qua 1 intent và hiệu ứng
     *
     * @param intent - intent
     * @param anim   - hiệu ứng
     */
    public void startActivity(@NonNull Intent intent, int anim) {
        mActivity.startActivity(intent);
        setActivityTransactionAnimation(ActivityTransition.FINISH);
    }

    /**
     * Phương thức khởi chạy 1 activity thông qua tên class
     *
     * @param clazz - tên_lớp.class
     */
    public void startActivity(@NonNull Class<? extends Activity> clazz) {
        Intent intent = new Intent(mActivity, clazz);
        startActivity(intent);
    }

    /**
     * Phương thức khởi chạy 1 activity thông qua tên class và hiệu ứng khởi chạy
     *
     * @param clazz - tên_lớp.class
     * @param anim  - hiệu ứng
     */
    public void startActivity(@NonNull Class<? extends Activity> clazz, int anim) {
        Intent intent = new Intent(mActivity, clazz);
        startActivity(intent, anim);
    }

    /**
     * Phương thức khởi chạy 1 activity có tham số là tên activity và 1 bundle
     *
     * @param clazz - tên_lớp.class
     * @param args  - bundle
     */
    public void startActivity(@NonNull Class<? extends Activity> clazz, Bundle args) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtras(args);
        startActivity(intent);
    }

    /**
     * Phương thức khởi chạy 1 activity và loại bỏ hết các activity khởi stack
     *
     * @param clazz - tên_lớp.class
     */
    public void startActivityAtRoot(@NonNull Class<? extends Activity> clazz) {
        Intent intent = new Intent(mActivity, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    /**
     * Phươn thức khởi chạy 1 activity có đợi kq trả về
     *
     * @param intent      - intent
     * @param requestCode - request code
     */
    public void startActivityForResult(@NonNull Intent intent, int requestCode) {
        mActivity.startActivityForResult(intent, requestCode);
        setActivityTransactionAnimation(ActivityTransition.START);
    }

    /**
     * Phươn thức khởi chạy 1 activity có đợi kq trả về
     *
     * @param clazz       - tên_lớp.class
     * @param args        - bundle
     * @param requestCode - request code
     */
    public void startActivityForResult(@NonNull Class<? extends Activity> clazz, Bundle args,
                                       int requestCode) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtras(args);
        startActivityForResult(intent, requestCode);
    }

    /**
     * Phương thức finish activity
     */
    public void finishActivity() {
        mActivity.finish();
        setActivityTransactionAnimation(ActivityTransition.FINISH);
    }

    /**
     * Phương thức finish activity với kết quả trả về
     *
     * @param intent     - intent
     * @param resultCode - request code
     */
    public void finishActivityWithResult(Intent intent, int resultCode) {
        mActivity.setResult(resultCode, intent);
        finishActivity();
    }

    /**
     * Điều hướng tới 1 địa chỉ url
     *
     * @param url -địa chỉ web
     */
    public void openUrl(String url) {
        if (TextUtils.isEmpty(url) || !Patterns.WEB_URL.matcher(url).matches()) {
            return;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
        startActivity(intent);
    }

    /**
     * Thêm 1 fragment lên activity hiện tại
     *
     * @param containerViewId - id của layout muốn hiển thị fragment
     * @param fragment        - fragment muốn hiển thị
     * @param addToBackStack  - cờ có cho vào stack fragment hay không
     * @param animation       - hiệu ứng khi fragment được thêm
     * @param tag             - thẻ của fragment được thêm
     */
    public void addFragment(@IdRes int containerViewId, Fragment fragment,
                            boolean addToBackStack, int animation, String tag) {
        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
        setFragmentTransactionAnimation(transaction, animation);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.replace(containerViewId, fragment, tag);
        transaction.commit();
    }

    /**
     * Thêm 1 fragment lên fragment hiện tại
     *
     * @param containerViewId - id của layout muốn hiển thị fragment
     * @param fragment        - fragment muốn hiển thị
     * @param addToBackStack  - cờ có cho vào stack fragment hay không
     * @param animation       - hiệu ứng khi fragment được thêm
     * @param tag             - thẻ của fragment được thêm
     */
    public void goNextChildFragment(@IdRes int containerViewId, Fragment fragment,
                                    boolean addToBackStack, int animation, String tag) {
        FragmentTransaction transaction = mFragment.getChildFragmentManager().beginTransaction();
        setFragmentTransactionAnimation(transaction, animation);
        if (addToBackStack) {
            transaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        transaction.replace(containerViewId, fragment, tag);
        transaction.commitAllowingStateLoss();
        mFragment.getChildFragmentManager().executePendingTransactions();
    }

    /**
     * Phương thức trở về từ 1 fragment con của 1 fragment đảm bảo luôn có 1 fragment đang hiện
     *
     * @return - trở về/gỡ fragment có thành công hay không
     */
    public boolean goBackChildFragment() {
        boolean isShowPrevious = mFragment.getChildFragmentManager().getBackStackEntryCount() > 1;
        if (isShowPrevious) {
            mFragment.getChildFragmentManager().popBackStackImmediate();
        }

//        FragmentManager fm = getFragmentManager();
//        fm.beginTransaction()
//                .setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out)
//                .hide(somefrag)
//                .show()
//                .commit();
        return isShowPrevious;
    }

    /**
     * Phương thức truyền hiệu ứng cho fragment
     *
     * @param transaction
     * @param animation
     */
    private void setFragmentTransactionAnimation(FragmentTransaction transaction,
                                                 @NavigateAnim int animation) {
        switch (animation) {
            case NavigateAnim.FADED:
                transaction.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out,
                        android.R.anim.fade_in, android.R.anim.fade_out);
                break;
            case NavigateAnim.RIGHT_LEFT:
                transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_left_out,
                        R.anim.slide_left_in, R.anim.slide_right_out);
                break;
            case NavigateAnim.LEFT_RIGHT:
                transaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_right_out,
                        R.anim.slide_right_in, R.anim.slide_left_out);
                break;
            case NavigateAnim.BOTTOM_UP:
                transaction.setCustomAnimations(R.anim.slide_bottom_in, R.anim.slide_top_out,
                        R.anim.slide_top_in, R.anim.slide_bottom_out);
                break;
            case NavigateAnim.NONE:
                break;
            default:
                break;
        }
    }

    /**
     * Phương thức truyền hiệu ứng cho activity
     *
     * @param animation
     */
    private void setActivityTransactionAnimation(@ActivityTransition int animation) {
        switch (animation) {
            case ActivityTransition.START:
                mActivity.overridePendingTransition(R.anim.translate_left, R.anim.translate_still);
                break;
            case ActivityTransition.FINISH:
                mActivity.overridePendingTransition(R.anim.translate_still, R.anim.translate_right);
                break;
            case ActivityTransition.NONE:
                break;
            default:
                break;
        }
    }

    /**
     * Phương thức ẩn bàn phím
     */
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = mActivity.getCurrentFocus();
        if (view == null) {
            view = new View(mActivity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    @IntDef({
            NavigateAnim.RIGHT_LEFT, NavigateAnim.BOTTOM_UP, NavigateAnim.FADED, NavigateAnim.NONE,
            NavigateAnim.LEFT_RIGHT
    })
    public @interface NavigateAnim {
        int NONE = 0x00;
        int RIGHT_LEFT = 0x01;
        int BOTTOM_UP = 0x02;
        int FADED = 0x03;
        int LEFT_RIGHT = 0x04;
    }

    @IntDef({ActivityTransition.NONE, ActivityTransition.START, ActivityTransition.FINISH})
    public @interface ActivityTransition {
        int NONE = 0x00;
        int START = 0x01;
        int FINISH = 0x02;
    }
}
