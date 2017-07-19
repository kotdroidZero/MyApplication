package com.example.user.myapplication.pojo;

import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

/**
 * Created by user on 14/7/17.
 */

public class Tab {
  public String tabTitle;
  public int tabIcon;
  public android.app.Fragment tabFragment;
  public boolean isShowTabTitle;





    public Tab(String tabName, int tabIcon, android.app.Fragment tabFragment, boolean isTitleShown) {
        this.tabTitle = tabName;
        this.tabIcon = tabIcon;
        this.tabFragment = tabFragment;
        this.isShowTabTitle = isTitleShown;
    }
}
