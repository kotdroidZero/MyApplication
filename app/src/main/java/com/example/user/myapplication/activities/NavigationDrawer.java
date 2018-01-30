package com.example.user.myapplication.activities;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.user.myapplication.R;
import com.example.user.myapplication.fragments.NavigationDrawerFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationDrawer extends AppCompatActivity {


    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    NavigationDrawerFragment drawerFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        ButterKnife.bind(this);
        setUpToolbar();


        init();
    }

    private void setUpToolbar() {
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void init() {
        drawerFragment= (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_fragment);
        drawerFragment.setUp(drawerLayout,toolbar);

    }
}
