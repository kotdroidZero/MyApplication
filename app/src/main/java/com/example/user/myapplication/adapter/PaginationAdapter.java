package com.example.user.myapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.pojo.PagingModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 13/7/17.
 */

public class PaginationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private boolean loading=true;


    public final static int ITEM=0;
    public final static int LOADER=1;
    List<PagingModel> list;
    Context context;


    public PaginationAdapter(List<PagingModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case ITEM: return new ItemHolder(LayoutInflater.from(context).inflate(R.layout.item_paging_content,parent,false));
           // case LOADER: return new LoaderHolder(LayoutInflater.from(context).inflate(R.layout.footer_loader_item,parent,false));
            default: return null;
        }
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemHolder)
        {
            ((ItemHolder) holder).tvEmail.setText(list.get(position).emailId);
            ((ItemHolder) holder).tvName.setText(list.get(position).name);
        }
       /* else if (holder instanceof LoaderHolder)
        {
            if (loading) {
                ((LoaderHolder) holder).progressBar.setVisibility(View.VISIBLE);
                if(list.size()<100)
                {
                    ((PaginationExample)context).addNewDataAgain();
                    setLoaded(true);
                }

                if (list.size()==100)
                {
                    ((LoaderHolder) holder).progressBar.setVisibility(View.GONE);
                    setLoaded(false);
                    ((LoaderHolder) holder).tvDone.setVisibility(View.VISIBLE);
                }

            }
            else
            {
                ((LoaderHolder) holder).progressBar.setVisibility(View.GONE);
                setLoaded(false);
            }

        }*/
    }

    @Override
    public int getItemCount() {
        //return list.size()+1;
        return  list.size();
    }


    public void updateList()
    {
        //list1.clear();
        //this.list.addAll(list1);
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position)
    {
        if (position==list.size()) {
            //return LOADER;
            return 89;
        }
        else
            return ITEM;

    }

    public void setLoaded(boolean isLoading) {
            loading=isLoading;
    }

    public  class  LoaderHolder extends RecyclerView.ViewHolder{
       @BindView(R.id.progressbar)
        ProgressBar progressBar;
        @BindView(R.id.tv_done)
        TextView tvDone;
        public LoaderHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public  class  ItemHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_email)
        TextView tvEmail;

        public ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
