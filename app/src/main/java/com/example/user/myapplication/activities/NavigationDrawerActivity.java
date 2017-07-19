package com.example.user.myapplication.activities;

import android.animation.ValueAnimator;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.user.myapplication.helpers.GeneralFunctions;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavigationDrawerActivity extends AppCompatActivity {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    public static final int HOME_FRAGMENT=0;
    public static final int MOVIES_FRAGMENT=1;
    public static final int NOTIFICATIONS_FRAGMENT=2;
    public static final int PHOTOS_FRAGMENT=3;
    public static final int PROFILE_FRAGMENT=4;
    public static final int SETTING_FRAGMENT=5;
    public static final int ABOUT_US=6;
    public static final int PRIVACY_POLICY=7;
    int selectedItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigaion_drawer);
        init();
        navigationMenuCustomization();
        setUpNavigationView();
    }

    private void init() {
        ButterKnife.bind(this);
    }

    private void navigationMenuCustomization() {
        navigationView.getMenu().getItem(3).setActionView(R.layout.menu_dot);
        navigationView.getMenu().getItem(2).setActionView(R.layout.arrow_more);
    }


    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //item.setCheckable(true);
                //item.setChecked(true);
                return selectedNavigationMenu(item);

            }
        });
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {}

            @Override
            public void onDrawerOpened(View drawerView){}

            @Override
            public void onDrawerClosed(View drawerView) {
               openSelectedFragment();
            }

            @Override
            public void onDrawerStateChanged(int newState) {}
        });
    }

    private void openSelectedFragment() {
        switch (selectedItem)
        {
            case HOME_FRAGMENT:
                //Fragment fragment=new HomeFragment();
                loadFragment(new HomeFragment(),"Home");
                break;
            case SETTING_FRAGMENT:
                loadFragment(new SettingFragment(),"Setting");
                break;
            case NOTIFICATIONS_FRAGMENT:
                loadFragment(new NotificationsFragment(),"Notification");
                break;
            case MOVIES_FRAGMENT:
                loadFragment(new MoviesFragment(),"Movies");
                break;
            case PHOTOS_FRAGMENT:
                loadFragment(new PhotosFragment(),"Photos");
                break;
            case ABOUT_US:
                Toast.makeText(NavigationDrawerActivity.this, "Nothing is available !", Toast.LENGTH_SHORT).show();
                break;
            case PRIVACY_POLICY:
                Toast.makeText(NavigationDrawerActivity.this, "Privacy Policy is loading....!", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private boolean selectedNavigationMenu(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.nav_home:
                selectedItem =HOME_FRAGMENT;
                break;
            case R.id.nav_settings:
                selectedItem =SETTING_FRAGMENT;
                break;
            case R.id.nav_notofications:
                selectedItem =NOTIFICATIONS_FRAGMENT;
                break;
            case R.id.nav_movies:
                selectedItem =MOVIES_FRAGMENT;
                break;
            case R.id.nav_photos:
                selectedItem =PHOTOS_FRAGMENT;
                break;
            case R.id.nav_about_us:
                selectedItem=ABOUT_US;
                break;
            case R.id.nav_privacy_policy:
                selectedItem=PRIVACY_POLICY;
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.END);
        return true;
    }

    public void loadFragment(final Fragment fragment, String tag)
    {
        FragmentTransaction transaction=getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame,fragment,tag);


       // transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        transaction.commit();
    }
    //this method is not require in case of menu
   /* public void loadFragment(Fragment fragment,String fragmentTag)
    {
        FragmentManager manager=getFragmentManager();
        String backStackName=fragmentTag;
        boolean fragmentPopped=manager.popBackStackImmediate(backStackName,0);

        if (!fragmentPopped&&manager.findFragmentByTag(fragmentTag)==null)
        {
            FragmentTransaction ft=manager.beginTransaction();
            ft.replace(R.id.frame,fragment,fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(fragmentTag);
            ft.commit();
      }
     }*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification,menu);
        return true;
    }
    public void openDrawer()
    {
        drawerLayout.openDrawer(GravityCompat.END);
    }
}
