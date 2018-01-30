package com.example.user.myapplication.helpers;

import android.os.Environment;

/**
 * Created by user on 20/7/17.
 */

public class Constants {
    public static final int ISTOUPDATE=256;
    public static final int ISTOSAVE=123;
    public static final int GET_PROFILE = 256;
    public static final int CAMRA_REQUEST = 120;
    public static final int CONTACT_ADD_REQUEST = 786;
    public static String imageDirectory=Environment.getExternalStorageDirectory().getAbsolutePath()+"/MyApplication/downsampled_image_folder";

}
