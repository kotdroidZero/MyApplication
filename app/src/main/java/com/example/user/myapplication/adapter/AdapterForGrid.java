package com.example.user.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.pojo.CustomModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 10/7/17.
 */

public class AdapterForGrid extends RecyclerView.Adapter<AdapterForGrid.RecycleViewHolder> {
    List<CustomModel> list;
    Context context;

    public AdapterForGrid(List<CustomModel> list, Context context) {
        this.list = list;
        this.context = context;


    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecycleViewHolder(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        holder.tvDate.setText(list.get(position).date.toString());
        holder.imageView.setImageDrawable(context.getApplicationContext().getResources().getDrawable(list.get(position).image));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RecycleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_date)
        TextView tvDate;

        @BindView(R.id.iv_imageview)
        ImageView imageView;
        public RecycleViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
