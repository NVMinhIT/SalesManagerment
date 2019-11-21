package com.example.salesmanagerment.screen.sales.customer.choosecustomer;

import android.content.Intent;
import android.os.Bundle;

import com.example.salesmanagerment.R;
import com.example.salesmanagerment.base.BaseActivity;
import com.example.salesmanagerment.utils.Navigator;

public class ListCustomerActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_customer);
        Intent intent = getIntent();
        String customerID = intent.getStringExtra(ListCustomerFragment.ARG_CUSTOMER_ID);
        mNavigator.addFragment(R.id.content_add, ListCustomerFragment.newInstance(customerID), false, Navigator.NavigateAnim.NONE, ListCustomerFragment.class.getSimpleName());
    }
}
