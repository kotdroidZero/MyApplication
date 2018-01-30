package com.example.user.myapplication.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.fragments.HomeFragment;
import com.example.user.myapplication.fragments.MoviesFragment;
import com.example.user.myapplication.fragments.NotificationsFragment;
import com.example.user.myapplication.fragments.PhotosFragment;
import com.example.user.myapplication.fragments.SettingFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationDrawerActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    View navHeader;
    FragmentManager manager;
    FragmentTransaction transaction;

    String[] activityTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigaion_drawer);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);


        navHeader=navigationView.getHeaderView(0);
        activityTitles=getResources().getStringArray(R.array.nav_item_activity_titles);
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);


        setUpNavigationView();



    }

    private void fragmentSetup() {
        manager=getFragmentManager();
        transaction=manager.beginTransaction();
    }

    private void setUpNavigationView() {
        fragmentSetup();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getGroupId())
                {
                    case R.id.nav_home:
                        transaction.add(R.id.frame,new HomeFragment(),"Home").commit();
                        break;
                    case R.id.nav_settings:
                        transaction.add(R.id.frame,new SettingFragment(),"Home").commit();
                        break;
                    case R.id.nav_notofications:
                        transaction.add(R.id.frame,new NotificationsFragment(),"Home").commit();
                        break;
                    case R.id.nav_movies:
                        transaction.add(R.id.frame,new MoviesFragment(),"Home").commit();
                        break;
                    case R.id.nav_photos:
                        transaction.add(R.id.frame,new PhotosFragment(),"Home").commit();
                        break;
                    case R.id.nav_about_us:
                        Toast.makeText(NavigationDrawerActivity.this, "Nothing is available !", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_privacy_policy:
                        Toast.makeText(NavigationDrawerActivity.this, "Privacy Policy is loading....!", Toast.LENGTH_SHORT).show();
                        break;


                }

                return true;
            }
        });


        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer,R.string.closeDrawer){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

}
