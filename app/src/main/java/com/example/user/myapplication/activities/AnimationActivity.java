package com.example.user.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.user.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AnimationActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.flipper)
    ViewFlipper flipper;

    @BindView(R.id.tv_listpopupwindow)
    TextView tvPopup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        ButterKnife.bind(this);

        registeringListeners();
    }

    private void registeringListeners() {
        flipper.setOnClickListener(this);
        tvPopup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.flipper:

               /* flipper.startFlipping();
                flipper.setFlipInterval(2000);
                flipper.setOutAnimation(AnimationActivity.this,R.anim.hyperspace_out);
                break;*/


            case R.id.tv_listpopupwindow:
                //listPopup();
                break;
        }
    }

    private void listPopup() {
         final String[] mStrings = { "Push up", "Push left", "Cross fade",
                "Hyperspace" };

        final ListPopupWindow listPopupWindow=new ListPopupWindow(this);
        listPopupWindow.setAdapter(new ArrayAdapter<String>(AnimationActivity.this,android.R.layout.simple_list_item_1,
                android.R.id.text1,mStrings));
        listPopupWindow.setAnchorView(tvPopup);
        listPopupWindow.setModal(true);

        listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tvPopup.setText(mStrings[position]);
                switch (position)
                {
                    case 0:
                    case 1:
                    case 2:
                    case 3:
                        flipper.startFlipping();
                        flipper.setFlipInterval(2000);
                       // flipper.setInAnimation(AnimationUtils.loadAnimation(AnimationActivity.this,R.anim.hyperspace_in));
                        flipper.setOutAnimation(AnimationUtils.loadAnimation(AnimationActivity.this,R.anim.scale));
                        break;
                }
                listPopupWindow.dismiss();
            }
        });

        listPopupWindow.show();


    }
}
