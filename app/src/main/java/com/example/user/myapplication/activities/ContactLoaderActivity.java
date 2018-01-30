package com.example.user.myapplication.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.adapter.ContactAdapter;
import com.example.user.myapplication.applications.BaseActivity;
import com.example.user.myapplication.database.DBHelper;
import com.example.user.myapplication.helpers.Constants;
import com.example.user.myapplication.helpers.ContactLoader;
import com.example.user.myapplication.interfaces.OnLoadFinished;
import com.example.user.myapplication.pojo.PhoneBook;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContactLoaderActivity extends BaseActivity implements OnLoadFinished {


    @BindView(R.id.recyclerview)
    RecyclerView recyclerView;
    @BindView(R.id.iv_addontacts)
    ImageView ivAddContacts;
    ContactLoader contactLoader;
    DBHelper dbHelper;
    private PhoneBook model;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_loader);

        init();



        contactLoader=new ContactLoader(this,this,getLoaderManager());
        ivAddContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(ContactLoaderActivity.this,AddContactActivity.class), Constants.CONTACT_ADD_REQUEST);

            }
        });

    }

    private void init() {
        ButterKnife.bind(this);
        dbHelper=new DBHelper(this);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Constants.CONTACT_ADD_REQUEST)
        {
            try {
                model= (PhoneBook) data.getExtras().getSerializable("phoneModel");
                if (resultCode==Constants.ISTOSAVE)
                {
                    long id=dbHelper.insertContact(model);
                    if (id>0)
                    {
                        PhoneBook model1=dbHelper.getLatestInsertedContact();
                        adapter.addContact(model1);
                        Toast.makeText(this, "Saved",Toast.LENGTH_SHORT).show();
                    }
                }
                else if (resultCode==Constants.ISTOUPDATE)
                {
                    int id=dbHelper.updateContact(model);
                    if (id>0)
                    {
                        adapter.addContactList(dbHelper.getAllContacts());

                        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onLoadFinished() {
        List<PhoneBook> listUpdated= dbHelper.getAllContacts();
        recyclerView.setLayoutManager(new LinearLayoutManager(ContactLoaderActivity.this,LinearLayoutManager.VERTICAL,false));
        adapter=new ContactAdapter(listUpdated,ContactLoaderActivity.this);
        recyclerView.setAdapter(adapter);
        //adapter.addContactList(listUpdated);
    }

}
