package com.example.user.myapplication.activities;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.myapplication.R;
import com.example.user.myapplication.adapter.FragmentAdapter;
import com.example.user.myapplication.fragments.Fragment1;
import com.example.user.myapplication.fragments.Fragment2;
import com.example.user.myapplication.interfaces.DataListeners;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FragmentsActivity extends AppCompatActivity implements DataListeners {


    @BindView(R.id.viewpager)
    public ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        ButterKnife.bind(this);

        List<Fragment> fragmentList=new ArrayList<>();
        fragmentList.add(new Fragment1());
        fragmentList.add(new Fragment2());

        FragmentAdapter adapter=new FragmentAdapter(getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);




    }

    @Override
    public void setExtraBundle(Bundle bundle) {

        Fragment2 fragment2=new Fragment2();
        fragment2.getData(bundle);
    }
}
