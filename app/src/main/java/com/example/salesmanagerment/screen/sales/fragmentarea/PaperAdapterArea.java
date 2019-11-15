package com.example.salesmanagerment.screen.sales.fragmentarea;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class PaperAdapterArea extends FragmentPagerAdapter {

    private int numberTab = 5;
    public PaperAdapterArea(FragmentManager fg , int numberTab){
        super(fg);
        this.numberTab= numberTab;
    }
    @NotNull
    @Override
    public Fragment getItem(int position) {
        return AreaFragmentOne.newInstance("Tầng" + position);
    }
    @Override
    public int getCount() {
        return numberTab;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "Tầng " + (position +1);
    }
}