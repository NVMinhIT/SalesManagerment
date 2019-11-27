package com.example.salesmanagerment.screen.infoapp.profile_user;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.data.model.entity.UserProfile;
import com.example.salesmanagerment.utils.CacheManager;

public class ProfileUserActivity extends AppCompatActivity implements View.OnClickListener {
    ImageButton imageButtonBack;
    private TextView tvName, tvCode, tvSex, tvBirthday, tvEmail, tvPhone, tvWordPosition;
    private UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        initView();
        initEvent();
    }

    private void initEvent() {
        imageButtonBack.setOnClickListener(this);
    }

    private void initView() {
        userProfile = new UserProfile();
        userProfile = CacheManager.cacheManager.getUser();
        tvName = findViewById(R.id.tvNameUser);
        tvSex = findViewById(R.id.tv_Sex);
        tvEmail = findViewById(R.id.tv_Email);
        tvPhone = findViewById(R.id.tv_Phone);
        tvWordPosition = findViewById(R.id.tv_Position_Work);
        showData();
        imageButtonBack = findViewById(R.id.imb_Back);

    }

    private void showData() {
        tvName.setText(userProfile.getDisplayName());
        tvEmail.setText(userProfile.getEmail());
        tvPhone.setText(userProfile.getMobile());

    }

    @Override
    public void onClick(View view) {
        finish();
    }
}
