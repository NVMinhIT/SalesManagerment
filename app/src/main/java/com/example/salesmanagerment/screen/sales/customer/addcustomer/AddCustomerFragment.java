package com.example.salesmanagerment.screen.sales.customer.addcustomer;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseFragment;
import com.example.salesmanagerment.data.model.entity.Customer;
import com.example.salesmanagerment.screen.sales.customer.choosecustomer.ListCustomerActivity;
import com.example.salesmanagerment.utils.CommonFunc;
import com.example.salesmanagerment.utils.Navigator;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

public class AddCustomerFragment extends BaseFragment implements View.OnClickListener, IAddCustomerContact.IView {
    private TextView tvBirthday;
    private ImageButton imgCancel;
    private Button btAdd;
    private int mYear, mMonth, mDay;
    private Calendar calendar;
    private AddCustomerPresenter addCustomerPresenter;
    private Customer mCustomer;
    private EditText edtCodeCustomer, edtNameCustomer, edtPhoneNumber, edtAddress;
    private Navigator navigator;

    public static AddCustomerFragment newInstance() {
        AddCustomerFragment dialogFragmentAddCustomer = new AddCustomerFragment();
        return dialogFragmentAddCustomer;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_customer, container, false);
        baseInit();
        initView(view);
        addCustomerPresenter = new AddCustomerPresenter();
        addCustomerPresenter.setView(this);


        initEvents();

        return view.getRootView();
    }

    private void initView(View view) {
        navigator = new Navigator(this);
        edtCodeCustomer = view.findViewById(R.id.edtCodeCustomer);
        edtNameCustomer = view.findViewById(R.id.edtNameCustomer);
        edtPhoneNumber = view.findViewById(R.id.edtPhoneNumber);
        tvBirthday = view.findViewById(R.id.tvBirthday);
        edtAddress = view.findViewById(R.id.edtAddress);
        imgCancel = view.findViewById(R.id.img_Cancel);
        btAdd = view.findViewById(R.id.btn_Save);


    }

    private void initEvents() {
        tvBirthday.setOnClickListener(this);
        btAdd.setOnClickListener(this);
        imgCancel.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvBirthday:
                showCalendar();
                break;

            case R.id.img_Cancel:
                mActivity.getSupportFragmentManager().popBackStack();
                navigator.hideKeyboard();
                break;
            case R.id.btn_Save:
                mCustomer = new Customer(UUID.randomUUID().toString(), edtCodeCustomer.getText().toString(),
                        edtNameCustomer.getText().toString(), CommonFunc.getDateServer(tvBirthday.getText().toString()), null, edtPhoneNumber.getText().toString(),
                        edtAddress.getText().toString(), null, false);
                addCustomerPresenter.customer = mCustomer;
                addCustomerPresenter.addCustomer();


            default:
                break;

        }
    }

    private void showCalendar() {
        calendar = Calendar.getInstance();
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
        mMonth = calendar.get(Calendar.MONTH) + 1;
        mYear = calendar.get(Calendar.YEAR);

        DatePickerDialog datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker datePicker, int dayOfMonth, int monthOfYear, int year) {
                calendar.set(dayOfMonth, monthOfYear, year);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                final String strDate = simpleDateFormat.format(calendar.getTime());
                tvBirthday.setText(strDate);
            }
        }, mDay, mMonth, mYear);
        datePickerDialog.show();
    }

    @Override
    public void gotoListCustomerScreen() {
        Intent intent = new Intent(ListCustomerActivity.ACTION_ADD_CUSTOMER);
        LocalBroadcastManager.getInstance(Objects.requireNonNull(getActivity())).sendBroadcast(intent);
        mActivity.finish();
    }

    @Override
    public void showLoading(boolean isShowLoading) {
        showDialog(isShowLoading);
    }
}
