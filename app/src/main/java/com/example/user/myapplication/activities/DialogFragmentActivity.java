package com.example.user.myapplication.activities;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.user.myapplication.R;
import com.example.user.myapplication.fragments.MyDialogFragments;

public class DialogFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_fragment);

        showDialogs();
    }

    private void showDialogs() {
        MyDialogFragments myDialogFragments=MyDialogFragments.newInstance("Choose option");
        myDialogFragments.show(getFragmentManager(),"picture dialog");

    }
}
