package com.example.salesmanagerment.screen.report;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.salesmanagerment.R;

public class ReportsFragment extends Fragment implements DialogDayToDay.ISetTimes {
    private static final String TAG = ReportsFragment.class.getSimpleName();
    private ImageView imageViewDrop;
    private TextView tvTimeReport;
    private static final int TARGET_FRAGMENT_REQUEST_CODE = 1;
    private static final String EXTRA_GREETING_MESSAGE = "message";
    private static final String EXTRA_GREETING_MESSAGES = "message1";
    private static final String EXTRA_GREETING_MESSAGESS = "message1";

    public static ReportsFragment newInstance() {
        return new ReportsFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        tvTimeReport = view.findViewById(R.id.tv_Time_Report);
        imageViewDrop = view.findViewById(R.id.img_Down_Arrow);
        imageViewDrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragmentReport dialogFragment = DialogFragmentReport.getInstance();
                dialogFragment.setTargetFragment(ReportsFragment.this, TARGET_FRAGMENT_REQUEST_CODE);
                DialogDayToDay dayToDay = DialogDayToDay.getInstance();
                dayToDay.setTargetFragment(ReportsFragment.this, TARGET_FRAGMENT_REQUEST_CODE);
                if (getFragmentManager() != null) {
                    dialogFragment.show(getFragmentManager(), DialogFragmentReport.TAG);
                }


            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == TARGET_FRAGMENT_REQUEST_CODE) {
            String time = null;
            if (data != null) {
                time = data.getStringExtra(EXTRA_GREETING_MESSAGE);


            }
            tvTimeReport.setText(time);

        }
    }

    public static Intent newIntent(String message) {
        Intent intent = new Intent();
        intent.putExtra(EXTRA_GREETING_MESSAGE, message);
        return intent;
    }


    @Override
    public void setTime(String day1, String day2) {
        StringBuffer stringBuffer = new StringBuffer(day1);
        stringBuffer.append(day2);
        tvTimeReport.setText(stringBuffer);


    }
}
