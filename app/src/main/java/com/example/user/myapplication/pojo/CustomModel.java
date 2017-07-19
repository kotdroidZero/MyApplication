package com.example.user.myapplication.pojo;

/**
 * Created by user on 10/7/17.
 */

public class CustomModel {
    public String date;
    public int image;
    public boolean isHeader;


    public CustomModel(String date, int image, boolean isHeader) {
        this.date = date;
        this.image = image;
        this.isHeader=isHeader;
    }
}
