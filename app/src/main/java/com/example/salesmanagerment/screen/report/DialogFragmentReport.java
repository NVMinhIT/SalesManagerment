package com.example.salesmanagerment.screen.report;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.salesmanagerment.R;


import java.util.Objects;

public class DialogFragmentReport extends DialogFragment implements View.OnClickListener {
    private ImageView imageViewClose;
    private TextView textViewOther;
    public static final String TAG = DialogFragmentReport.class.getSimpleName();
    private static final int TARGET_FRAGMENT_REQUEST_CODE = 1;
    private DialogDayToDay dialogDayToDay;
    private TextView tvTimeNear, tvThisWeek, tvAgoWeek, tvThisMonth, tvAgoMonth, tvTimeOther;

    public static DialogFragmentReport getInstance() {
        DialogFragmentReport fragment = new DialogFragmentReport();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_fragment_options_report, container, false);
        initView(view);
        return view.getRootView();
    }

    private void initView(View view) {
        tvTimeNear = view.findViewById(R.id.tv_time_near);
        tvThisWeek = view.findViewById(R.id.tv_this_week);
        tvAgoWeek = view.findViewById(R.id.tv_ago_week);
        tvThisMonth = view.findViewById(R.id.tv_this_month);
        tvAgoMonth = view.findViewById(R.id.tv_ago_month);
        tvTimeNear.setOnClickListener(this);
        tvThisWeek.setOnClickListener(this);
        tvAgoWeek.setOnClickListener(this);
        tvThisMonth.setOnClickListener(this);
        tvAgoMonth.setOnClickListener(this);
        dialogDayToDay = new DialogDayToDay();
        imageViewClose = view.findViewById(R.id.img_Close_Dialog);
        textViewOther = view.findViewById(R.id.tv_time_other);
        textViewOther.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogDayToDay dialogFragment = DialogDayToDay.getInstance();
                dialogFragment.setTargetFragment(DialogFragmentReport.this, TARGET_FRAGMENT_REQUEST_CODE);
                if (getFragmentManager() != null) {
                    dialogFragment.show(getFragmentManager(), DialogDayToDay.TAG);
                }
            }
        });

        imageViewClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
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
            case R.id.tv_time_near:
                String time1 = tvTimeNear.getText().toString();
                sendResult(time1);
                break;
            case R.id.tv_this_week:
                String time2 = tvThisWeek.getText().toString();
                sendResult(time2);
                break;
            case R.id.tv_ago_week:
                String time3 = tvAgoWeek.getText().toString();
                sendResult(time3);
                break;
            case R.id.tv_this_month:
                String time4 = tvThisMonth.getText().toString();
                sendResult(time4);
                break;
            case R.id.tv_ago_month:
                String time5 = tvAgoMonth.getText().toString();
                sendResult(time5);
                break;
        }
    }

    private void sendResult(String time1) {
        if (getTargetFragment() == null) {
            return;
        }
        Intent intent = ReportsFragment.newIntent(time1);
        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);
        dismiss();
    }
}
