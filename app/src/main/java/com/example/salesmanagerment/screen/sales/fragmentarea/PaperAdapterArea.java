package com.example.salesmanagerment.screen.sales.fragmentarea;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import org.jetbrains.annotations.NotNull;

public class PaperAdapterArea extends FragmentPagerAdapter {

    private int numberTab;
    public PaperAdapterArea(FragmentManager fg , int numberTab){
        super(fg);
        this.numberTab= numberTab;
    }
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AreaFragmentOne();
            case 1:
                return  new AreaFragmentTwo();
            case 2:
                return  new AreaFragmentThree();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return numberTab;
    }
}