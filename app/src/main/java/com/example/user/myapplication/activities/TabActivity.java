package com.example.user.myapplication.activities;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.myapplication.R;
import com.example.user.myapplication.adapter.TabAdapter;
import com.example.user.myapplication.fragments.HomeFragment;
import com.example.user.myapplication.fragments.ProfileFragment;
import com.example.user.myapplication.fragments.SettingFragment;
import com.example.user.myapplication.pojo.Tab;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabActivity extends AppCompatActivity {

    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tablayout)
    TabLayout tabLayout;
    private List<Tab> tabList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        ButterKnife.bind(this);

        prepareListData();
        adapterSetting();
        settingTabIcon();

    }

    private void settingTabIcon() {
        for (int i = 0; i< tabList.size(); i++)
        {
            tabLayout.getTabAt(i).setIcon(tabList.get(i).tabIcon);

        }
    }

    private void prepareListData() {
        tabList =new ArrayList<>();
        tabList.add(new Tab("Home",R.drawable.home_fragment_selectors,new HomeFragment(),true));
        tabList.add(new Tab("Setting",R.drawable.ic_settings,new SettingFragment(),false));
        tabList.add(new Tab("Profile",R.drawable.profile_fragment_selectors,new ProfileFragment(),true));
    }

    private void adapterSetting() {
        tabLayout.setupWithViewPager(viewPager);
        TabAdapter adapter=new TabAdapter(getFragmentManager(), tabList);
        viewPager.setAdapter(adapter);
    }
}
