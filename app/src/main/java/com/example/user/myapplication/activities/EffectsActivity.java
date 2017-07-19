package com.example.user.myapplication.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.example.user.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EffectsActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.btn_ripple)
    Button btnRipple;

    @BindView(R.id.content)
    ScrollView scrollView;

    @BindView(R.id.progressbar)
    ProgressBar progressBar;

    int shortANimDuration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_effects);

        ButterKnife.bind(this);
        scrollView.setVisibility(View.GONE);

        shortANimDuration=getResources().getInteger(android.R.integer.config_longAnimTime);

        //animation
        crossFade();
        versionCheckin();



        listeners();
    }

    private void crossFade() {
        scrollView.setAlpha(1f);
        scrollView.setVisibility(View.VISIBLE);

        scrollView.animate()
                .alpha(1f)
                .setDuration(shortANimDuration)
                .setListener(null);

        progressBar.animate()
                .alpha(0f)
                .setDuration(shortANimDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        scrollView.setVisibility(View.GONE);
                    }
                });
    }

    private void versionCheckin() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            btnRipple.setBackground(getDrawable(R.drawable.ripple));
        else
            btnRipple.setBackgroundDrawable(getResources().getDrawable(R.drawable.ripple_via_selectors));
    }

    private void listeners() {
        btnRipple.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_ripple:

                break;
        }
    }
}
