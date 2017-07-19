package com.example.user.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimActivity extends AppCompatActivity implements Animation.AnimationListener {


    @BindView(R.id.tv_textview)
    TextView textView;
    @BindView(R.id.tv_blink)
    TextView tvBlink;
    @BindView(R.id.tv_zoomin)
    TextView tvZoomIn;
    @BindView(R.id.tv_rotate)
    TextView tvRotate;
    @BindView(R.id.tv_move)
    TextView tvMove;
    @BindView(R.id.tv_slideup)
    TextView tvSlideUp;
    @BindView(R.id.tv_slidedown)
    TextView tvSlideDown;
    @BindView(R.id.tv_sequential_animation)
    TextView tvSequential;

    Animation animationFadeIn,animationFadeOut,animationBlink,animationZoomIn,animationZoomOut,animationRotate,animationMove;
    Animation animationSlideUp,animationSlideDown,animationSequential;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);

        ButterKnife.bind(this);
        loadAnimation();
        settingListeners();
        startingAnimation();


    }

    private void startingAnimation() {
        textView.startAnimation(animationFadeIn);
        tvBlink.setAnimation(animationBlink);
        tvZoomIn.setAnimation(animationZoomIn);
        tvRotate.setAnimation(animationRotate);
        tvMove.setAnimation(animationMove);
        tvSlideUp.setAnimation(animationSlideUp);
        tvSlideDown.setAnimation(animationSlideDown);
        tvSequential.setAnimation(animationSequential);
    }

    private void settingListeners() {
        animationFadeIn.setAnimationListener(this);
        animationFadeOut.setAnimationListener(this);
        animationBlink.setAnimationListener(this);
        animationZoomIn.setAnimationListener(this);
        animationZoomOut.setAnimationListener(this);
        animationRotate.setAnimationListener(this);
        animationMove.setAnimationListener(this);
        animationSlideUp.setAnimationListener(this);
        animationSlideDown.setAnimationListener(this);
        animationSequential.setAnimationListener(this);
    }

    private void loadAnimation() {
        animationFadeIn= AnimationUtils.loadAnimation(this,R.anim.fade_in1);
        animationFadeOut=AnimationUtils.loadAnimation(this,R.anim.fade_out1);
        animationBlink=AnimationUtils.loadAnimation(this,R.anim.blink_1);
        animationZoomIn=AnimationUtils.loadAnimation(this,R.anim.zoom_in1);
        animationZoomOut=AnimationUtils.loadAnimation(this,R.anim.zoom_out1);
        animationRotate=AnimationUtils.loadAnimation(this,R.anim.rotate_1);
        animationMove=AnimationUtils.loadAnimation(this,R.anim.move_using_translate1);
        animationSlideUp=AnimationUtils.loadAnimation(this,R.anim.slideup_using_scale1);
        animationSlideDown=AnimationUtils.loadAnimation(this,R.anim.slidedown_using_scale1);
        animationSequential=AnimationUtils.loadAnimation(this,R.anim.sequential_animation);

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation==animationFadeIn)
        {
            Toast.makeText(this, "Animation End", Toast.LENGTH_SHORT).show();
            textView.startAnimation(animationFadeOut);
            tvZoomIn.setAnimation(animationZoomOut);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
