package com.example.user.myapplication.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.activities.MainActivity;
import com.example.user.myapplication.interfaces.OnLoadMoreListener;
import com.example.user.myapplication.pojo.CustomModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 10/7/17.
 */

public class Adapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int LOADMORE =2 ;
    List<CustomModel> list;
     MainActivity context;
    OnLoadMoreListener loadListener;
    int visibleThreshHold=5,lastVisibleItem,totalItemCount;
    boolean isLoading;
    public static final int PICTURES=0;
    public static final int HEADERS=1;


    public Adapter2(final List<CustomModel> list,final MainActivity context) {
        this.list = list;
        this.context = context;

        final GridLayoutManager manager= (GridLayoutManager) context.recyclerView.getLayoutManager();
        context.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                totalItemCount=manager.getItemCount();
                lastVisibleItem=manager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount<=(lastVisibleItem+visibleThreshHold))
                {
                    if (loadListener!=null)
                    {
                        loadListener.onLoadMore();
                        Toast.makeText(context, list.size()+"", Toast.LENGTH_SHORT).show();
                    }
                    isLoading=true;
                }

            }
        });
    }
    public void setOnLoadMoreListener(OnLoadMoreListener mOnLoadMoreListener) {
        this.loadListener = mOnLoadMoreListener;
    }
    public void setLoaded() {
        isLoading = false;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType)
        {
            case LOADMORE : return new Holder3(LayoutInflater.from(context).inflate(R.layout.footer_loader_item,parent,false));
            case PICTURES: return new Holder1(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
            case HEADERS:  return new Holder2(LayoutInflater.from(context).inflate(R.layout.section_header,parent,false));
            default:return new Holder1(LayoutInflater.from(context).inflate(R.layout.item,parent,false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder1)
        {
            ((Holder1) holder).imageView.setImageResource(list.get(position).image);
            ((Holder1) holder).tvDate.setText(list.get(position).date);
        }
        else if (holder instanceof Holder2)
        {
            String d=list.get(position).date;
            String day=d.substring(d.indexOf('5')+1,d.length());

            ((Holder2) holder).tvHeader.setText(day);
        }
        else if (holder instanceof Holder3)
        {
            if (position==list.size())
            {
                //((Holder3) holder).tvLoadMore.setText("list Finished");
            }
            else
            {
               // ((Holder3) holder).tvLoadMore.setText("loading more item");
            }

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (list.get(position)==null)
        {
            return LOADMORE;
        }
        else if (list.get(position).isHeader)
        {
            return HEADERS;
        }
        else
        {
            return PICTURES;
        }

    }

    public class Holder1 extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_date)
        TextView tvDate;

        @BindView(R.id.iv_imageview)
        ImageView imageView;
        public Holder1(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);


        }
    }

    public  class Holder3 extends  RecyclerView.ViewHolder{

       /* @BindView(R.id.tv_load_more)
        TextView tvLoadMore;*/
        @BindView(R.id.progressbar)
       public   ProgressBar progressBar;
        public Holder3(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


    }

   public  class Holder2 extends RecyclerView.ViewHolder{

       @BindView(R.id.tv_header)
       public TextView tvHeader;

       public Holder2(View itemView) {
           super(itemView);
           ButterKnife.bind(this,itemView);

       }


   }
}
