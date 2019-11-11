package com.example.salesmanagerment.screen.report;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.salesmanagerment.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class DialogDayToDay extends DialogFragment {
    TextView textViewStart, textViewEnd;
    private int mYear, mMonth, mDay;
    TextView tStartDay, tEndDay;
    Calendar calendar;
    private Button btnCancel, btnYes;
    public ISetTimes iSetTime;

    public static final String TAG = DialogDayToDay.class.getSimpleName();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_options_day, container, false);
        initView(view);

        return view.getRootView();
    }

    public interface ISetTimes {
        void setTime(String day1, String day2);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ISetTimes) {
            iSetTime = (ISetTimes) getTargetFragment();
        }

    }

    public static DialogDayToDay getInstance() {
        DialogDayToDay dayToDay = new DialogDayToDay();
        return dayToDay;
    }

    private void initView(View view) {
        btnCancel = view.findViewById(R.id.btn_No_Time);
        btnYes = view.findViewById(R.id.btn_Yes_Time);

        textViewStart = view.findViewById(R.id.tv_day_start);
        textViewEnd = view.findViewById(R.id.tv_day_end);
        tStartDay = view.findViewById(R.id.txt_day_start);
        tEndDay = view.findViewById(R.id.txt_day_end);
        textViewStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == textViewStart) {
                    calendar = Calendar.getInstance();
                    mDay = calendar.get(Calendar.DAY_OF_MONTH);
                    mMonth = calendar.get(Calendar.MONTH) + 1;
                    mYear = calendar.get(Calendar.YEAR);

                    DatePickerDialog datePickerDialog = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                        datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                            @SuppressLint("SetTextI18n")
                            @Override
                            public void onDateSet(DatePicker datePicker, int dayOfMonth, int monthOfYear, int year) {
                                calendar.set(dayOfMonth, monthOfYear, year);
                                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                final String strDate = simpleDateFormat.format(calendar.getTime());
                                tStartDay.setText(strDate);
                            }
                        }, mDay, mMonth, mYear);
                    }
                    datePickerDialog.show();
                }


            }
        });
        textViewEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                mDay = calendar.get(Calendar.DAY_OF_MONTH);
                mMonth = calendar.get(Calendar.MONTH) + 1;
                mYear = calendar.get(Calendar.YEAR);

                DatePickerDialog datePickerDialog = null;
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                    datePickerDialog = new DatePickerDialog(Objects.requireNonNull(getActivity()), R.style.DialogTheme, new DatePickerDialog.OnDateSetListener() {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onDateSet(DatePicker datePicker, int dayOfMonth, int monthOfYear, int year) {
                            calendar.set(dayOfMonth, monthOfYear, year);
                            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                            final String strDateEnd = simpleDateFormat.format(calendar.getTime());
                            tEndDay.setText(strDateEnd);
                        }
                    }, mDay, mMonth, mYear);
                }
                datePickerDialog.show();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sday = tStartDay.getText().toString();
                String eday = tEndDay.getText().toString();
                iSetTime.setTime(sday, eday);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    Objects.requireNonNull(getDialog()).dismiss();
                }

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


}
