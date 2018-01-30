package com.example.user.myapplication.applications;

import android.app.Application;

import com.example.user.myapplication.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by user on 20/7/17.
 */

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/ProximaSemibold.otf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

}
