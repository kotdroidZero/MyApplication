package com.example.user.myapplication.fragments;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.activities.NavigationDrawerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 14/7/17.
 */

public class PhotosFragment extends android.app.Fragment  implements SensorEventListener, SensorListener {

    private static final int THRES_HOLD =1800 ;
    @BindView(R.id.iv_hamburger)
    ImageView ivHamburger;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    NavigationDrawerActivity activity;

    SensorManager sensorManager;
    private long lastUpdate;
    private float x,y,z;
    private float last_z,last_x,last_y;
    private float diffTime;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_photos,container,false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        activity=(NavigationDrawerActivity)getActivity();
        sensorManager= (SensorManager) activity.getSystemService(Context.SENSOR_SERVICE);
        sensorWorks();
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

    private void sensorWorks() {
        sensorManager.registerListener(this,SensorManager.SENSOR_ACCELEROMETER);

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

    @Override
    public void onSensorChanged(SensorEvent event) {
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {
        if (sensor==SensorManager.SENSOR_ACCELEROMETER)
        {
            long currTime=System.currentTimeMillis();

            if (currTime-lastUpdate>100)
            {
                lastUpdate=currTime;
            }

            x=values[SensorManager.DATA_X];
            y=values[SensorManager.DATA_Y];
            z=values[SensorManager.DATA_Z];


            float speed=Math.abs(x+y+z-last_x-last_y-last_z)/diffTime*10000;
            if (speed>THRES_HOLD)
            {
                Vibrator v= (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(3000);
                Toast.makeText(activity, "shake", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {

    }
}
