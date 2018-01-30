package com.example.user.myapplication.pojo;

import java.io.Serializable;

/**
 * Created by user on 19/7/17.
 */

public class PhoneBook implements Serializable{
   private String name;
   private String phoneNumber;
   private String profile;
   private String lastContacted;
    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public PhoneBook() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getLastContacted() {
        return lastContacted;
    }

    public void setLastContacted(String lastContacted) {
        this.lastContacted = lastContacted;
    }
}
