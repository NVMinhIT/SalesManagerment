package com.example.salesmanagerment.screen.infoapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;


public class InfoAppActivity extends BaseActivity {
    private ImageButton imageButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_app);
        initView();
    }

    private void initView() {
        imageButtonBack = findViewById(R.id.imb_Back_Info);
        imageButtonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
