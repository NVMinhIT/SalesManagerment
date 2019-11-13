package com.example.salesmanagerment.screen.main;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.screen.more.MoreFragment;
import com.example.salesmanagerment.screen.paydish.PayDishFragment;
import com.example.salesmanagerment.screen.provisional.ProvisionalFragment;
import com.example.salesmanagerment.screen.sales.listorder.ListOrderFragment;
import com.example.salesmanagerment.utils.Navigator;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Toolbar mToolbar;
    private TextView tvTitle;
    View contentView;
    BottomNavigationView bottomNavigationView;
    private ImageView imageViewDown;
    private int currentIDFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initEvents();
    }

    /**
     * Phương thức gắn sự kiện cho view
     */
    private void initEvents() {
        imageViewDown.setOnClickListener(this);
        tvTitle.setOnClickListener(this);
    }

    /**
     * Phương thức tham chiếu, khởi tạo view
     */
    private void initViews() {
        try {
            tvTitle = findViewById(R.id.tv_Title_Main);
            imageViewDown = findViewById(R.id.imv_down);
            contentView = findViewById(R.id.content_layout);
            mToolbar = findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            bottomNavigationView = findViewById(R.id.bottom_nav);
            bottomNavigationView.setSelectedItemId(R.id.action_order);
            setupBottomNav(bottomNavigationView.getSelectedItemId());
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    setupBottomNav(item.getItemId());
                    return true;
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupBottomNav(int selectedItemId) {
        if (currentIDFragment == selectedItemId) {
            return;
        }
        switch (selectedItemId) {
            case R.id.action_order:
                mNavigator.addFragment(R.id.flMainContainer, ListOrderFragment.newInstance(), false, Navigator.NavigateAnim.NONE, ListOrderFragment.class.getSimpleName());
                tvTitle.setText(R.string.ordering);
                imageViewDown.setVisibility(View.VISIBLE);
                break;
            case R.id.action_pay_dish:
                tvTitle.setText(R.string.pay_dish);
                imageViewDown.setVisibility(View.GONE);
                mNavigator.addFragment(R.id.flMainContainer, PayDishFragment.newInstance(), false, Navigator.NavigateAnim.NONE, PayDishFragment.class.getSimpleName());
                break;
            case R.id.action_caculater:
                tvTitle.setText(R.string.tamtinh);
                imageViewDown.setVisibility(View.GONE);
                mNavigator.addFragment(R.id.flMainContainer, ProvisionalFragment.newInstance(), false, Navigator.NavigateAnim.NONE, ProvisionalFragment.class.getSimpleName());
                break;
            case R.id.action_more:
                tvTitle.setText(R.string.more);
                imageViewDown.setVisibility(View.GONE);
                mNavigator.addFragment(R.id.flMainContainer, MoreFragment.newInstance(), false, Navigator.NavigateAnim.BOTTOM_UP, MoreFragment.class.getSimpleName());
                break;
            default:
                break;
        }
        currentIDFragment = selectedItemId;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_Title_Main:
            case R.id.imv_down:
                showOrderSelected();
                break;
            default:
                break;
        }
    }

    private void showOrderSelected() {
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, imageViewDown);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.one:
                        tvTitle.setText(R.string.ordering);
                        break;
                    case R.id.two:
                        tvTitle.setText(R.string.require_pay);
                        break;
                    case R.id.three:
                        tvTitle.setText(R.string.order_before);
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        popupMenu.show();
    }
}
