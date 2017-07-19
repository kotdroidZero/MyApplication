package com.example.user.myapplication.adapter;


import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import com.example.user.myapplication.pojo.Tab;

import java.util.List;

/**
 * Created by user on 14/7/17.
 */

public class TabAdapter extends FragmentPagerAdapter {


    private List<Tab> list;
    public TabAdapter(FragmentManager fm,List<Tab> list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position).tabFragment;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (list.get(position).isShowTabTitle)
        {
            return list.get(position).tabTitle;
        }
        else
            return null;
    }
}
