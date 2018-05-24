package com.example.hendawy.metro.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.hendawy.metro.FilterManager;

import java.util.ArrayList;
import java.util.List;

public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();
    private Context context;
    private FilterManager filterManager;

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public TabsPagerAdapter(Context context, FragmentManager fm,
                            FilterManager filterManager) {
        super(fm);
        this.context = context;
        this.filterManager = filterManager;
    }
    @Override
    public Fragment getItem(int position) {

        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}

