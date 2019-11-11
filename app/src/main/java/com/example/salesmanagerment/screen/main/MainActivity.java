package com.example.salesmanagerment.screen.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.screen.authentication.changepassword.ChangePassActivity;
import com.example.salesmanagerment.screen.authentication.logout.LogOutDialogFragment;
import com.example.salesmanagerment.screen.chooseinventoryitem.InventoryItemActivity;
import com.example.salesmanagerment.screen.infoapp.InfoAppActivity;
import com.example.salesmanagerment.screen.report.ReportsFragment;
import com.example.salesmanagerment.screen.sales.SalesFragment;
import com.example.salesmanagerment.utils.Navigator;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String EXTRA_CLASS = "MainActivity3";
    private Navigator mNavigator;
    private DrawerLayout drawerLayout;
    private NavigationView navView;
    private ActionBarDrawerToggle drawerToggle;
    private Toolbar mToolbar;
    private TextView tvTitle;
    private FloatingActionButton floatingActionButton;
    private boolean mIsSale = false;
    View contentView;
    Navigator navigator;
    BottomNavigationView bottomNavigationView;
    private LogOutDialogFragment logOutDialogFragment;
    private ImageView imageViewDown;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigator = new Navigator(this);

        initViews();
        initEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    /**
     * Phương thức gắn sự kiện cho view
     */
    private void initEvents() {
        imageViewDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, imageViewDown);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());

                //registering popup with OnMenuItemClickListener
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
//                        Toast.makeText(MainActivity.this, "You Clicked : " + item.getTitle(), Toast.LENGTH_SHORT).show();
                        switch (item.getItemId()) {
                            case R.id.one:
                                tvTitle.setText(R.string.ordering);
                                break;
                            case R.id.two:
                                tvTitle.setText(R.string.require_pay);
                                break;
                            case R.id.three:
                                break;
                            default:
                                break;

                        }
                        return true;
                    }
                });

                popupMenu.show();//showing popup menu
            }
        });//closing the setOnClickListener method

//        btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                try {
//                    if (mIsSale) {
//                        //vào màn hình thêm món oder
//                        //mNavigator.startActivity(OrderInventoryItemActivity.class);
//                    } else {
//                        //vào màn hình món ăn,thực đơn, thêm món ăn
//                        //mNavigator.startActivityForResult(AddItemInventoryActivity.class, new Bundle(), Constants.REQUEST_CODE);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
    }

    /**
     * Phương thức tham chiếu, khởi tạo view
     */
    private void initViews() {
        try {

            floatingActionButton = findViewById(R.id.fab);
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navigator.startActivity(InventoryItemActivity.class);
                }
            });
            logOutDialogFragment = new LogOutDialogFragment();
            navigator = new Navigator(this);
            tvTitle = findViewById(R.id.tv_Title_Main);
            imageViewDown = findViewById(R.id.imv_down);
            contentView = findViewById(R.id.content_layout);
            mToolbar = findViewById(R.id.toolbar);
            setSupportActionBar(mToolbar);
            drawerLayout = findViewById(R.id.drawerLayout);
            drawerToggle = new ActionBarDrawerToggle(
                    this, drawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            drawerLayout.addDrawerListener(drawerToggle);
            drawerToggle.syncState();
            navView = findViewById(R.id.navView);
            navView.setVerticalScrollBarEnabled(false);
            navView.setNavigationItemSelectedListener(this);
            mIsSale = true;
            navigator.addFragment(R.id.flMainContainer, SalesFragment.newInstance(), false, Navigator.NavigateAnim.NONE, SalesFragment.class.getSimpleName());
            tvTitle.setText(R.string.ordering);
            imageViewDown.setVisibility(View.VISIBLE);
            bottomNavigationView = findViewById(R.id.bottom_nav);
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
        switch (selectedItemId) {
            case R.id.action_order:
                mIsSale = true;
                //navigator.addFragment(R.id.flMainContainer, SellFragment.newInstance(), false, Navigator.NavigateAnim.NONE, SellFragment.class.getSimpleName());
                tvTitle.setText(R.string.ordering);
                imageViewDown.setVisibility(View.VISIBLE);
                break;
            case R.id.action_pay_dish:
                //navigator.startActivity(PayDishActivity.class);

            default:
        }
    }

    @Override
    public void onBackPressed() {
        try {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navSale:
                try {
                    tvTitle.setText(R.string.ordering);
                    mIsSale = true;
                    mNavigator.addFragment(R.id.flMainContainer, SalesFragment.newInstance(), false, Navigator.NavigateAnim.LEFT_RIGHT, SalesFragment.class.getSimpleName());
                    bottomNavigationView.setVisibility(View.VISIBLE);
                    imageViewDown.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.navReport:
                try {
                    tvTitle.setText(R.string.menu_report);
                    mIsSale = false;
                    mNavigator.addFragment(R.id.flMainContainer, ReportsFragment.newInstance(), false, Navigator.NavigateAnim.RIGHT_LEFT, ReportsFragment.class.getSimpleName());
                    bottomNavigationView.setVisibility(View.GONE);
                    imageViewDown.setVisibility(View.GONE);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case R.id.navChangePassword:
                try {
                    //tvTitle.setText(R.string.menu_report);
                    mIsSale = false;
                    navigator.startActivity(ChangePassActivity.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.navInfo:
                try {
                    mIsSale = false;
                    navigator.startActivity(InfoAppActivity.class);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.navLogout:
                try {
                    mIsSale = false;
                    getSupportFragmentManager().beginTransaction().add(logOutDialogFragment, EXTRA_CLASS).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


}
