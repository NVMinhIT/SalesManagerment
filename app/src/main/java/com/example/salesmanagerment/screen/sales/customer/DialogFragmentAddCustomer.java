package com.example.salesmanagerment.screen.sales.customer;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.salesmanagerment.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class DialogFragmentAddCustomer extends DialogFragment implements View.OnClickListener {

    private TextView textViewOptionDate;
    private ImageButton imgCancel;
    private Button btAdd;
    private int mYear, mMonth, mDay;
    private Calendar calendar;

    public static DialogFragmentAddCustomer newInstance() {
        DialogFragmentAddCustomer dialogFragmentAddCustomer = new DialogFragmentAddCustomer();
        return dialogFragmentAddCustomer;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_customer, container, false);
        initView(view);
        initEvents();

        return view.getRootView();
    }

    private void initView(View view) {
        imgCancel = view.findViewById(R.id.img_Cancel);
        btAdd = view.findViewById(R.id.btn_Save);
        textViewOptionDate = view.findViewById(R.id.tv_Option_Date);

    }

    private void initEvents() {
        textViewOptionDate.setOnClickListener(this);
        btAdd.setOnClickListener(this);
        imgCancel.setOnClickListener(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        try {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            Objects.requireNonNull(dialog.getWindow()).requestFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                @Override
                public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                    // Disable Back key and Search key
                    switch (keyCode) {
                        case KeyEvent.KEYCODE_SEARCH:
                            return true;
                        case KeyEvent.KEYCODE_BACK:
                            return true;
                        default:
                            return false;
                    }
                }
            });
            return dialog;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onCreateDialog(savedInstanceState);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        try {
            WindowManager.LayoutParams params = Objects.requireNonNull(Objects.requireNonNull(getDialog()).getWindow()).getAttributes();
            params.width = getResources().getDimensionPixelSize(R.dimen.dialog_with);
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            Objects.requireNonNull(getDialog().getWindow()).setAttributes(params);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_Option_Date:
                showCalendar();
                break;

            case R.id.img_Cancel:
                dismiss();
                break;
            case R.id.btn_Save:


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
                textViewOptionDate.setText(strDate);
            }
        }, mDay, mMonth, mYear);
        datePickerDialog.show();
    }
}
