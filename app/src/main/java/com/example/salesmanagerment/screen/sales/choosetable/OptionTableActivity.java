package com.example.salesmanagerment.screen.sales.choosetable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.listeners.IOnItemClickListener;
import com.example.salesmanagerment.data.model.response.base.Table;
import com.example.salesmanagerment.screen.sales.fragmentarea.PaperAdapterArea;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class OptionTableActivity extends AppCompatActivity implements View.OnClickListener, IOnItemClickListener<Table> {
    private Button btCancelTable;
    private ImageButton imageButtonBack;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private PaperAdapterArea paperAdapter;
    private TabItem tabItem1, tabItem2, tabItem3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_table);
        initView();
    }

    private void initView() {

        btCancelTable = findViewById(R.id.btn_Cancel_Table);
        imageButtonBack = findViewById(R.id.btn_Back_Table);
        btCancelTable.setOnClickListener(this);
        imageButtonBack.setOnClickListener(this);
        initTablayout();
    }

    private void initTablayout() {
        viewPager = findViewById(R.id.view_paper);
        tabLayout = findViewById(R.id.tab_layout);
        tabItem1 = findViewById(R.id.tabone);
        tabItem2 = findViewById(R.id.tabtwo);
        tabItem3 = findViewById(R.id.tabthree);
        paperAdapter = new PaperAdapterArea(getSupportFragmentManager(), tabLayout.getTabCount());
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
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    @Override
    public void onItemClick(Table data) {
        Intent intent = new Intent();
        intent.putExtra("NAME", data.getNameTable());
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
