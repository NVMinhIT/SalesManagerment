package com.example.salesmanagerment.screen.authentication.changepassword;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesmanagerment.R;

public class ChangePassActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton imageButtonBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        initView();
    }

    private void initView() {
        imageButtonBack = findViewById(R.id.btn_back_logout);
        imageButtonBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back_logout:
                finish();
                break;
            default:
                break;
        }
    }
}
