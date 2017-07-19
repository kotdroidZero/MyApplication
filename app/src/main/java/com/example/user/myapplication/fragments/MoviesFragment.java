package com.example.user.myapplication.fragments;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationSet;
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

public class MoviesFragment extends android.app.Fragment {

    @BindView(R.id.iv_hamburger)
    ImageView ivHamburger;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_ball)
    TextView tvBall;
    NavigationDrawerActivity activity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movies,container,false);
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
        setBounceAnim();
    }

    private void setBounceAnim() {
        Vibrator vibrator= (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(5000);
        ObjectAnimator animator1= (ObjectAnimator) AnimatorInflater.loadAnimator(activity,R.animator.blink_1);
        animator1.setDuration(2000);

        animator1.start();
        animator1.setTarget(tvBall);
        ObjectAnimator animator2= (ObjectAnimator) AnimatorInflater.loadAnimator(activity,R.animator.zoom_in1);
        animator2.setDuration(2000);

        animator2.setStartDelay(3000);
        animator2.start();
        animator2.setTarget(tvBall);
        ObjectAnimator animator3= (ObjectAnimator) AnimatorInflater.loadAnimator(activity,R.animator.zoom_out1);
        animator2.setDuration(2000);
        animator3.setTarget(tvBall);
        animator3.setStartDelay(5000);
        animator3.start();
        animator3.setTarget(tvBall);

        ValueAnimator fadeAnim = ObjectAnimator.ofFloat(tvBall, "alpha", 1f, 0.8f);
        fadeAnim.setDuration(2000);

        //fadeAnim.start();

        /*AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.play(animator1);
        animatorSet.play(animator2);
        animatorSet.play(animator3);
        animatorSet.play(fadeAnim);
        animatorSet.start();*/
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
