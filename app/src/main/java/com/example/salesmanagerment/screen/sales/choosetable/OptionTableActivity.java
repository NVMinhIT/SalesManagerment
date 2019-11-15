package com.example.salesmanagerment.screen.sales.choosetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.viewpager.widget.ViewPager;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.data.model.entity.Area;
import com.example.salesmanagerment.data.model.entity.TableMappingCustom;
import com.example.salesmanagerment.screen.sales.fragmentarea.PaperAdapterArea;
import com.example.salesmanagerment.screen.sales.fragmentarea.TableAdapter;
import com.example.salesmanagerment.screen.sales.fragmentarea.TableFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class OptionTableActivity extends BaseActivity implements IOptionTableContact.IView, View.OnClickListener {
    private Button btCancelTable;
    private ImageButton imageButtonBack;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PaperAdapterArea paperAdapter;
    private OptionTablePresenter mPresenter;
    private Button btnChooseTable;
    private String NameTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_table);
        initView();
    }

    private void initView() {
        viewPager = findViewById(R.id.view_paper);
        tabLayout = findViewById(R.id.tab_layout);
        btCancelTable = findViewById(R.id.btn_Cancel_Table);
        imageButtonBack = findViewById(R.id.btn_Back_Table);
        btnChooseTable = findViewById(R.id.btn_Yes_Table);
        btnChooseTable.setOnClickListener(this);
        btCancelTable.setOnClickListener(this);
        imageButtonBack.setOnClickListener(this);
        mPresenter = new OptionTablePresenter();
        mPresenter.setView(this);
        mPresenter.getArea();
    }

    private void initTablayout(List<Area> areaList) {
        int size = areaList.size();
        for (int i = 0; i < size; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(areaList.get(i).AreaName));
            Log.d("MINH", "HIHI" + areaList.get(1).AreaName);
        }
        paperAdapter = new PaperAdapterArea(getSupportFragmentManager(), areaList);
        viewPager.setAdapter(paperAdapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Cancel_Table:
                // finish();
                break;
            case R.id.btn_Back_Table:
                finish();
                break;
            case R.id.btn_Yes_Table:


                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


//    @Override
//    public void onItemClick(Table data) {
//        Intent intent = new Intent();
//        intent.putExtra("NAME", data.getNameTable());
//        setResult(Activity.RESULT_OK, intent);
//        finish();
//    }

    @Override
    public void getAreaSuccess(List<Area> areaList) {
        initTablayout(areaList);
    }

    @Override
    public void showLoading(boolean isShowLoading) {
        //showLoading(isShowLoading);
    }


}
