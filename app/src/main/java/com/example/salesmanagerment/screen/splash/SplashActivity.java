package com.example.salesmanagerment.screen.splash;

import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.data.enums.EAppSate;
import com.example.salesmanagerment.screen.authentication.login.LoginActivity;
import com.example.salesmanagerment.screen.main.MainActivity;
import com.example.salesmanagerment.utils.Navigator;

public class SplashActivity extends AppCompatActivity {

    private Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        init();
    }

    private void init() {
        mNavigator = new Navigator(this);
        long splash_time = 3000L;
        Handler handler;
        ImageView imageViewIcon;
        TextView textViewName;
        imageViewIcon = findViewById(R.id.img_app_icon);
        textViewName = findViewById(R.id.text_view_app_name);
        final Animation animationIcon = AnimationUtils.loadAnimation(this, R.anim.show_icon_app);
        imageViewIcon.startAnimation(animationIcon);
        final Animation animationName = AnimationUtils.loadAnimation(this, R.anim.show_icon_app);
        textViewName.startAnimation(animationName);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (checkData() == EAppSate.NOT_LOG_IN) {
                    goToLoginScreen();
                } else {
                    goToMainScreen();
                }

            }
        }, splash_time);
    }

    private void goToMainScreen() {
        mNavigator.startActivityAtRoot(MainActivity.class);
    }

    /**
     * Kiểm tra trạng thái ứng dụng
     */
    private EAppSate checkData() {
        return EAppSate.NOT_LOG_IN;
    }

    private void goToLoginScreen() {
        mNavigator.startActivityAtRoot(LoginActivity.class);
    }
}
