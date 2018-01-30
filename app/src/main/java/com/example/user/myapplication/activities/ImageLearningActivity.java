package com.example.user.myapplication.activities;

import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.myapplication.BuildConfig;
import com.example.user.myapplication.R;
import com.example.user.myapplication.applications.BaseActivity;
import com.example.user.myapplication.helpers.Constants;
import com.example.user.myapplication.helpers.GeneralFunctions;
import com.example.user.myapplication.helpers.GetSampledImage;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageLearningActivity extends BaseActivity implements GetSampledImage.OnSampledImageAsyncTaskPostExecute{

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText tvPhone;
    @BindView(R.id.iv_addimage)

    ImageView ivAddImage;
    String picturePath;
    boolean isFromGallery;
    String imageDirectory=Constants.imageDirectory;


    boolean isCameraOptionSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_learning);

        init();
        listeners();
    }

    private void listeners() {
        ivAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setTitle("pic from");

        dialog.setItems(getResources().getStringArray(R.array.dialog_option), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which)
                {
                    case 0:
                        openCameraIntent();
                        break;
                    case 1:
                        openGalleryIntent();
                        break;
                    case 2:
                        break;
                }
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();




    }

    private void openGalleryIntent() {
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI), Constants.GET_PROFILE);
    }

    private void openCameraIntent() {
        Intent takePictureIntent = new Intent(
                MediaStore.ACTION_IMAGE_CAPTURE);
        File file = null;
        try
        {
            file= GeneralFunctions.setUpImageFile(Constants.imageDirectory);
            picturePath=file.getAbsolutePath();

            Uri outPutUri= FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID,file);
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,outPutUri);
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            {
                takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            else if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.JELLY_BEAN)
            {
                ClipData data=ClipData.newUri(getContentResolver(),"picture",outPutUri);
                takePictureIntent.setClipData(data);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            else
            {
                List<ResolveInfo> list=getPackageManager().queryIntentActivities
                        (takePictureIntent, PackageManager.MATCH_DEFAULT_ONLY);
                for (ResolveInfo resolveInfo:list)
                {
                    String packageName=resolveInfo.activityInfo.packageName;
                    grantUriPermission(packageName,outPutUri,Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

            }
            startActivityForResult(takePictureIntent,Constants.CAMRA_REQUEST);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void init() {
        ButterKnife.bind(ImageLearningActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {


            switch (requestCode) {
                case Constants.CAMRA_REQUEST:
                    isFromGallery=false;
                    break;
                case Constants.GET_PROFILE:
                    isFromGallery=true;
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    break;
            }

            new GetSampledImage(this).execute(picturePath,imageDirectory,
                    String.valueOf(isFromGallery),String.valueOf(getResources().getDimension(R.dimen.image_dimen)));
        }
    }

    @Override
    public void onSamplingPostExecute(File file) {
        Picasso.with(this).load(file).into(ivAddImage);
    }
}
