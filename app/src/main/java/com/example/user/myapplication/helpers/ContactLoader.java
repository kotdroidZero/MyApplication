package com.example.user.myapplication.helpers;

import android.app.LoaderManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;

import com.example.user.myapplication.interfaces.OnLoadFinished;
import com.example.user.myapplication.pojo.PhoneBook;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by user on 19/7/17.
 */

public class ContactLoader  implements LoaderManager.LoaderCallbacks<Cursor> {

    Context context;
    int LOADER_ID=0;
    LoaderManager manager;
    Loader<Cursor> loader;
    //CursorLoader cursorLoader=new CursorLoader();
    OnLoadFinished onLoadFinishedListener;
    private List<PhoneBook> list=new ArrayList<>();

    public List<PhoneBook> getList() {
        return list;
    }

    String NAME=ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME;
   // String PROFILE=ContactsContract.CommonDataKinds.Phone.IS_USER_PROFILE;
    String PHONE_NUMBER=ContactsContract.CommonDataKinds.Phone.NUMBER;
    String LAST_CONTACTED=ContactsContract.CommonDataKinds.Phone.LAST_TIME_CONTACTED;
    public ContactLoader(OnLoadFinished onLoadFinished,Context context,LoaderManager loaderManager) {
        this.context = context;
        manager=loaderManager;
        onLoadFinishedListener=onLoadFinished;
        loader=manager.initLoader(LOADER_ID,null,this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        //this is the uri for phones contacts.
        Uri contactsUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
       // Uri contactsUri = ContactsContract.Contacts.CONTENT_URI;

        // The columns to return for each row
        String[] projection = {NAME/*PROFILE*/,PHONE_NUMBER,LAST_CONTACTED} ;

        String selection = null;                                 //Selection criteria
        String[] selectionArgs = {};                             //Selection criteria
        String sortOrder = null;                                 //The sort order for the returned rows


        return new CursorLoader(context,contactsUri,projection,selection,selectionArgs,sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        data.moveToFirst();
        while (data.moveToNext())
        {
            String name=data.getString(data.getColumnIndex(NAME));
            String phoneNumber=data.getString(data.getColumnIndex(PHONE_NUMBER));
           // String profile=data.getString(data.getColumnIndex(PROFILE));
            String lastContacted=data.getString(data.getColumnIndex(LAST_CONTACTED));
            PhoneBook model=new PhoneBook();
            model.setName(name);
          //  model.setProfile(profile);
            model.setPhoneNumber(phoneNumber);
            model.setLastContacted(lastContacted);
            list.add(model);
        }
        Collections.sort(list, new Comparator<PhoneBook>() {
            @Override
            public int compare(PhoneBook o1, PhoneBook o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        onLoadFinishedListener.onLoadFinished();

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }


}
