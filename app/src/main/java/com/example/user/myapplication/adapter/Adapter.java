package com.example.user.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;

import com.example.user.myapplication.R;

import java.util.List;

/**
 * Created by user on 10/7/17.
 */

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Integer> list;
    Context context;
    private static final int HOLDER1=0;
    private static final int HOLDER2=1;


    public Adapter(List<Integer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case HOLDER1: return new Holder1(LayoutInflater.from(context).inflate(R.layout.item1,parent,false));
            case HOLDER2: return new Holder2(LayoutInflater.from(context).inflate(R.layout.item2,parent,false));
            default:return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        int a=list.get(position);
        if (a==0)
            return HOLDER2;
        else
            return HOLDER1;

    }

    public class Holder1 extends RecyclerView.ViewHolder{
        public Holder1(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    list.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }
    }

   public class Holder2 extends RecyclerView.ViewHolder{
       public Holder2(View itemView) {
           super(itemView);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   list.remove(getAdapterPosition());
                   notifyItemRemoved(getAdapterPosition());
               }
           });
       }
   }
}
