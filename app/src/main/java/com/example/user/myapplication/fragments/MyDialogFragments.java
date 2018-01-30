package com.example.user.myapplication.fragments;

import android.app.DialogFragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.activities.AddContactActivity;
import com.example.user.myapplication.dialogs.MyDialogFragment;
import com.example.user.myapplication.helpers.Constants;
import com.example.user.myapplication.interfaces.FileListener;

import java.io.File;
import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 20/7/17.
 */

public class MyDialogFragments extends DialogFragment implements View.OnClickListener,FileListener {


    @BindView(R.id.tv_viacamera)
    TextView tvCamera;
    @BindView(R.id.tv_viagallery)
    TextView tvGallery;
    public static String imageFolderName=Environment.getExternalStorageDirectory().getAbsolutePath()+"/Learning/downsampled_image_folder";
    File folderPath;
    String imageName;
    File imagePath;


    public MyDialogFragments() {

    }

    public static MyDialogFragments newInstance(String title)
    {
        MyDialogFragments fragment=new MyDialogFragments();
        Bundle bundle=new Bundle();
        bundle.putString("title",title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_fragment_take_pic_from,container);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        init();


        settingListeners();

    }

    private void init() {
        imageFolderName= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Learning/downsampled_image_folder";
        folderPath=new File(imageFolderName);
        if (!folderPath.exists())
        {
            folderPath.mkdirs();
        }
        String title=getArguments().getString("title");
        getDialog().setTitle(title);
       // getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    private void settingListeners() {
        tvCamera.setOnClickListener(this);
        tvGallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tv_viacamera:
                openCamera();
                break;
            case R.id.tv_viagallery:
                getImageFromPhone();
                break;
        }
    }

    private void openCamera() {
        Intent intentCamera=new Intent();
        intentCamera.setAction(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        imageName="Img"+System.currentTimeMillis()+".jpg";
        imagePath=new File(folderPath,imageName);
        intentCamera.putExtra("imagePath",imageName);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT,Uri.fromFile(imagePath));

        getActivity().startActivityForResult(intentCamera, Constants.CAMRA_REQUEST);

        dismiss();
    }
    public void getImageFromPhone() {
        Intent intentImage=new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intentImage.setType("image/*");
        Intent chooserIntent=Intent.createChooser(intentImage,"Select profile via");
        getActivity().startActivityForResult(chooserIntent,Constants.GET_PROFILE);
        dismiss();
    }

    @Override
    public Bundle getFileName() {
        Bundle bundle=new Bundle();
        bundle.putString("imageName",imageName);
        bundle.putString("folderName",imageFolderName);
        return bundle;
    }
}
