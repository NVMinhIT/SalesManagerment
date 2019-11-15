package com.example.salesmanagerment.screen.sales.fragmentarea;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.salesmanagerment.data.model.entity.Area;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PaperAdapterArea extends FragmentPagerAdapter {

    private List<Area> mAreas;

    public PaperAdapterArea(FragmentManager fg, List<Area> areaList) {
        super(fg);
        this.mAreas = areaList;
    }

    @NotNull
    @Override
    public Fragment getItem(int position) {
        return TableFragment.newInstance(mAreas.get(position).AreaID);
    }

    @Override
    public int getCount() {
        return mAreas.size();
    }
}