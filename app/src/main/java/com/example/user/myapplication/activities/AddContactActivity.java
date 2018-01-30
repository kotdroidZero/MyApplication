package com.example.user.myapplication.activities;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.applications.BaseActivity;
import com.example.user.myapplication.fragments.MyDialogFragments;
import com.example.user.myapplication.helpers.Constants;
import com.example.user.myapplication.helpers.GeneralFunctions;
import com.example.user.myapplication.interfaces.FileListener;
import com.example.user.myapplication.pojo.PhoneBook;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddContactActivity extends BaseActivity implements View.OnClickListener {


    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.iv_addimage)
    ImageView ivAddImage;
    @BindView(R.id.btn_save)
    Button btnSave;
    private String name,phone;
    private Uri profileUri;

    int btnValue;

    private String oldPic;
    private PhoneBook modelToUpdate;
    String imageFolderName;
    File folderPath;
    String imageName;
    File imagePath;
    FileListener fileListener;
    private MyDialogFragments myDialogFragments;
    private File fileToDownSample;
    private String folderName;
    private File sampledFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        init();
        onGettingIntent();

        listeners();
    }

    private void onGettingIntent() {
        if (getIntent().getExtras()!=null)
        {
            btnValue= Constants.ISTOUPDATE;
             modelToUpdate= (PhoneBook) getIntent().getExtras().getSerializable("model");
            etName.setText(modelToUpdate.getName());
            etPhone.setText(modelToUpdate.getPhoneNumber());
            oldPic=modelToUpdate.getProfile();
            Picasso.with(this).load(Uri.parse(modelToUpdate.getProfile())).into(ivAddImage);
        }
        else {
            btnValue=Constants.ISTOSAVE;
        }
    }

    private void listeners() {
        ivAddImage.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    private void init() {
        ButterKnife.bind(this);
        imageFolderName= Environment.getExternalStorageDirectory().getAbsolutePath()+"/Learning/downsampled_image_folder";
        folderPath=new File(imageFolderName);
        if (!folderPath.exists())
        {
            folderPath.mkdirs();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_addimage:
                showDialogs();
                //getImageFromPhone();
                break;
            case R.id.btn_save:
               performSavingInDB();
                break;
        }
    }
    private void showDialogs() {
        fileListener= myDialogFragments=MyDialogFragments.newInstance("Choose option");
        myDialogFragments.show(getFragmentManager(),"picture dialog");


    }
    private void performSavingInDB() {
        name = etName.getText().toString().trim();
        phone = etPhone.getText().toString().trim();
        Intent phoneModel = new Intent(AddContactActivity.this, ContactLoaderActivity.class);



        if (btnValue == Constants.ISTOSAVE) {
            modelToUpdate = new PhoneBook();
            modelToUpdate.setName(name);
            modelToUpdate.setPhoneNumber(phone);
            modelToUpdate.setProfile(profileUri.toString());
            Bundle bundle = new Bundle();
            bundle.putSerializable("phoneModel", modelToUpdate);
            phoneModel.putExtras(bundle);
            setResult(Constants.ISTOSAVE, phoneModel);
        } else {
            modelToUpdate.setName(name);
            modelToUpdate.setPhoneNumber(phone);
            modelToUpdate.setProfile(oldPic);

            Bundle bundle = new Bundle();
            bundle.putSerializable("phoneModel", modelToUpdate);
            phoneModel.putExtras(bundle);
            setResult(Constants.ISTOUPDATE, phoneModel);
        }
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //getting data if the picture is captured by camera
        if (requestCode==Constants.CAMRA_REQUEST&&resultCode==RESULT_OK)
        {

            Bundle bundle=fileListener.getFileName();
            String imageName=bundle.getString("imageName");
            folderName=bundle.getString("folderName");
            fileToDownSample =new File(new File(folderName),imageName);



        }
        if (requestCode==Constants.GET_PROFILE&&resultCode==RESULT_OK)
        {
            profileUri =data.getData();
            oldPic= profileUri.toString();
            String filePath=getFilePath(profileUri);


        }
    }

    private String getFilePath(Uri profileUri) {
        String[] filePathColumn={MediaStore.Images.Media.DATA};
        Cursor cursor=getContentResolver().query(profileUri,filePathColumn,null,null,null);
        cursor.moveToFirst();
        int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
        String filePath=cursor.getColumnName(columnIndex);
        cursor.close();
        return filePath;
    }


}
