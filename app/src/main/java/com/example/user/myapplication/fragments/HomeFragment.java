package com.example.user.myapplication.fragments;

import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.activities.NavigationDrawerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 14/7/17.
 */

public class HomeFragment extends android.app.Fragment {

    @BindView(R.id.iv_hamburger)
    ImageView ivHamburger;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    NavigationDrawerActivity activity;
    @BindView(R.id.tv_valueanimator)
    TextView textView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity=(NavigationDrawerActivity)getActivity();
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);



        ValueAnimator animator=ValueAnimator.ofFloat(0f,900f);
        animator.setDuration(3000);
        animator.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });
        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedvalue= (float) animation.getAnimatedValue();
                //textView.setTranslationY(animatedvalue);

            }
        });

        Keyframe kf1=Keyframe.ofFloat(0f,0f);
        Keyframe kf2=Keyframe.ofFloat(0.5f,360f);
        Keyframe kf3=Keyframe.ofFloat(1f,0f);

        PropertyValuesHolder pvhRotation=PropertyValuesHolder.ofKeyframe("rotation",kf1,kf2,kf3);
        ObjectAnimator rotationAnim=ObjectAnimator.ofPropertyValuesHolder(textView,pvhRotation);
        rotationAnim.setDuration(5000);
        rotationAnim.setRepeatCount(ValueAnimator.INFINITE);
        rotationAnim.setRepeatMode(ValueAnimator.REVERSE);
        rotationAnim.start();




        ivHamburger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((NavigationDrawerActivity)getActivity()).openDrawer();
            }
        });

        ObjectAnimator objectAnimator=ObjectAnimator.ofFloat(textView,"alpha",0f,1f);
        objectAnimator.setDuration(6000);
        objectAnimator.start();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        menu.clear();
        inflater.inflate(R.menu.notification,menu);
        //super.onCreateOptionsMenu(menu,inflater);
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
