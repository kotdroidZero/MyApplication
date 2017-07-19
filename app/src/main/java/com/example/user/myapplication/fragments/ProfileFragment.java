package com.example.user.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.activities.NavigationDrawerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 14/7/17.
 */

public class ProfileFragment extends android.app.Fragment {

    @BindView(R.id.iv_hamburger)
    ImageView ivHamburger;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    NavigationDrawerActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        activity=(NavigationDrawerActivity)getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);
        ivHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationDrawerActivity)getActivity()).openDrawer();
            }
        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu,inflater);menu.clear();
        inflater.inflate(R.menu.notification,menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_all_clear:
                Toast.makeText(getActivity(), "Clear all", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_mark_all_red:
                Toast.makeText(getActivity(), "Mark Read", Toast.LENGTH_SHORT).show();
                break;

        }
        return true;
    }
}
