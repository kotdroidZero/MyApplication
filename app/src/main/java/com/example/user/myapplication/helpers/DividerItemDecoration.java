package com.example.user.myapplication.helpers;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by user on 10/7/17.
 */

public class DividerItemDecoration extends RecyclerView.ItemDecoration {
    private Drawable mDivider;

    public DividerItemDecoration(Drawable mDivider) {
        this.mDivider = mDivider;
    }



    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view)==0)
        {
            return;
        }

        outRect.left=mDivider.getIntrinsicWidth();
    }

    @Override
    public void onDraw(Canvas canvass, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvass, parent, state);

        int top=parent.getPaddingTop();
        int bottom=parent.getHeight()-parent.getPaddingBottom();
        //int dividerLeft=parent.getPaddingLeft();
        //int dividerRight=parent.getWidth()-parent.getPaddingRight();

        int childCount=parent.getChildCount();

        for (int i=0;i<childCount-1;i++)
        {
            View child=parent.getChildAt(i);

            RecyclerView.LayoutParams layoutParams= (RecyclerView.LayoutParams) child.getLayoutParams();


            int left=child.getRight()+layoutParams.rightMargin;
            int right=left+mDivider.getIntrinsicWidth();

            int dividerTop=child.getBottom()+layoutParams.bottomMargin;
            int dividerBottom=dividerTop+mDivider.getIntrinsicHeight();


           // mDivider.setBounds(dividerLeft,dividerTop,dividerRight,dividerBottom);
            mDivider.setBounds(left,top,right,bottom);
            mDivider.draw(canvass);
        }


    }
/*
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (parent.getChildAdapterPosition(view)==0)
        {
            return;
        }

        outRect.top=mDivider.getIntrinsicHeight();
    }

    @Override
    public void onDraw(Canvas canvass, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvass, parent, state);

        int dividerLeft=parent.getPaddingLeft();
        int dividerRight=parent.getWidth()-parent.getPaddingRight();

        int childCount=parent.getChildCount();

        for (int i=0;i<childCount-1;i++)
        {
            View child=parent.getChildAt(i);

            RecyclerView.LayoutParams layoutParams= (RecyclerView.LayoutParams) child.getLayoutParams();

            int dividerTop=child.getBottom()+layoutParams.bottomMargin;
            int dividerBottom=dividerTop+mDivider.getIntrinsicHeight();


            mDivider.setBounds(dividerLeft,dividerTop,dividerRight,dividerBottom);
            mDivider.draw(canvass);
        }


    }*/


}
