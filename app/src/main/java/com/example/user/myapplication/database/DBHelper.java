package com.example.user.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.myapplication.pojo.PhoneBook;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 19/7/17.
 */

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="test_db";
    public static final int DATABASE_VERSION=3;
    public static final String TABLE_NAME="contact_table";
    public static final String UID="uid";
    public static final String NAME="name";
    public static final String PHONE_NUMBER="phone_number";
    public static final String PROFILE="profile";

    public static final String CREATE_TABLE=" CREATE TABLE "+TABLE_NAME+"( "
            +UID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +NAME +" TEXT, "
            +PHONE_NUMBER +" TEXT, "
            +PROFILE +" TEXT );";
    public static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_TABLE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            db.execSQL(DROP_TABLE);
            onCreate(db);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public long insertContact(PhoneBook model) {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,model.getName());
        contentValues.put(PHONE_NUMBER,model.getPhoneNumber());
        contentValues.put(PROFILE,model.getProfile());
        long id=sqLiteDatabase.insert(TABLE_NAME,null,contentValues);
        sqLiteDatabase.close();
        return id;
    }


    public List<PhoneBook> getAllContacts() {
        String SELECT_QUERY="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase dbRead=this.getReadableDatabase();
        Cursor cursor=dbRead.rawQuery(SELECT_QUERY,null);
        cursor.moveToFirst();
        List<PhoneBook> list=new ArrayList<>();
        while (cursor.moveToNext())
        {
            PhoneBook model=new PhoneBook();
            int uid=cursor.getInt(cursor.getColumnIndex(UID));
            String name=cursor.getString(cursor.getColumnIndex(NAME));
            String phone=cursor.getString(cursor.getColumnIndex(PHONE_NUMBER));
            String profile=cursor.getString(cursor.getColumnIndex(PROFILE));
            model.setName(name);
            model.setUid(uid);
            model.setProfile(profile);
            model.setPhoneNumber(phone);
            list.add(model);
        }
        return list;
    }

    public void deleteContactRow(PhoneBook model) {
        String DELETE_ROW="DELETE FROM "+TABLE_NAME+" WHERE "+UID+" = "+model.getUid() ;
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        //sqLiteDatabase.delete(TABLE_NAME,UID+"=?",new String[]{})
        sqLiteDatabase.execSQL(DELETE_ROW);
        sqLiteDatabase.close();
    }

    public int updateContact(PhoneBook model) {
        SQLiteDatabase dbWrite=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(NAME,model.getName());
        contentValues.put(PHONE_NUMBER,model.getPhoneNumber());
        contentValues.put(PROFILE,model.getProfile());
        int id=dbWrite.update(TABLE_NAME,contentValues, UID+" = ?",new String[]{String.valueOf(model.getUid())} );
        return id;
    }

    public PhoneBook getLatestInsertedContact() {
        String SELECT_QUERY="SELECT * FROM "+TABLE_NAME;
        SQLiteDatabase dbRead=this.getReadableDatabase();
        Cursor cursor=dbRead.rawQuery(SELECT_QUERY,null);
        cursor.moveToLast();
        PhoneBook latestInsertedContact=new PhoneBook();
        latestInsertedContact.setName(cursor.getString(cursor.getColumnIndex(NAME)));
        latestInsertedContact.setPhoneNumber(cursor.getString(cursor.getColumnIndex(PHONE_NUMBER)));
        latestInsertedContact.setProfile(cursor.getString(cursor.getColumnIndex(PROFILE)));
        latestInsertedContact.setUid(cursor.getInt(cursor.getColumnIndex(UID)));
        return latestInsertedContact;
    }
}
