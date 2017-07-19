package com.example.user.myapplication.activities;

import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.adapter.Adapter2;
import com.example.user.myapplication.interfaces.OnLoadMoreListener;
import com.example.user.myapplication.pojo.CustomModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {




    @BindView(R.id.recyclerview)
    public RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.multiautocompletetextview)
    MultiAutoCompleteTextView multiAutoCompleteTextView;

    List<String> diffDates=new ArrayList<>();
    @BindView(R.id.btn)
    Button btn;
    List<CustomModel> list;

    int numOfGrids=0;
    private List<CustomModel> list1;
    private Adapter2 adapter;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setData();
        settingListener();
        swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);
      //  multiAutoCompleteTextView();







    }

    private void multiAutoCompleteTextView() {

        multiAutoCompleteTextView.setVisibility(View.VISIBLE);
        String[] str={"Andoid","Jelly Bean","Froyo",
                "Ginger Bread","Eclipse Indigo","Eclipse Juno"};

        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        ArrayAdapter<String> adp=new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,str);

        multiAutoCompleteTextView.setThreshold(1);
        multiAutoCompleteTextView.setAdapter(adp);
    }

    private void settingListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItem();
            }
        });
    }

    private void refreshItem() {


            new Handler().postDelayed(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void run() {
                    swipeRefreshLayout.setRefreshing(false);
                }
            },3000);
        adapter=new Adapter2(getList(list1,1),this);
        recyclerView.setAdapter(adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void setData() {
      list = new ArrayList<>();
        getData(list);


       // recyclerView.setLayoutManager(new GridLayoutManager(this,2));
       // RecyclerView.ItemDecoration dividerDecoration = new DividerItemDecoration(getDrawable(R.drawable.divider));
        //recyclerView.addItemDecoration(dividerDecoration);

        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);


        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                if (adapter.getItemViewType(position)==Adapter2.HEADERS)
               {
                   return 2;
               }
               else if (adapter.getItemViewType(position)==Adapter2.PICTURES)
               {
                   return 1;
               }
               else if (adapter.getItemViewType(position)==Adapter2.LOADMORE)
                {
                    return 2;
                }
                else
                {
                    return  0;
                }
            }
        });

        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new Adapter2(list,this);
        adapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                list.add(null);
                adapter.notifyItemInserted(list.size()-1);

                //loading more data
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int start=list.size();
                        for (int i=0;i<20;i++)
                        {
                            CustomModel model=new CustomModel("Pushkar Sivastava User :"+ (i+1) ,0,false);
                            list.add(start+i,model);
                            Toast.makeText(MainActivity.this, list.size()+"", Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                        adapter.setLoaded();

                    }
                },5000);
            }
        });
        recyclerView.setAdapter(adapter);
        // recyclerView.setAdapter(new Adapter(list,this));
    }

    public void getData(List<CustomModel> list)
    {

        list.add(new CustomModel("20 May, 2017",0,true));
        list.add(new CustomModel("20 May, 2017",0,false));
        list.add(new CustomModel("20 May, 2017",0,false));
        list.add(new CustomModel("20 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,true));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,true));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("20 May, 2017",0,true));
        list.add(new CustomModel("20 May, 2017",0,false));
        list.add(new CustomModel("20 May, 2017",0,false));
        list.add(new CustomModel("20 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,true));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,true));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("23 May, 2017",0,true));
        list.add(new CustomModel("23 May, 2017",0,false));
        list.add(new CustomModel("23 May, 2017",0,false));
        list.add(new CustomModel("24 May, 2017",0,true));
        list.add(new CustomModel("24 May, 2017",0,false));
         list1=new ArrayList<>();

    }

    public List<CustomModel> getList(List<CustomModel> list,int a)
    {
        list.add(new CustomModel("28 May, 2017",0,true));
        list.add(new CustomModel("28 May, 2017",0,false));
        list.add(new CustomModel("28 May, 2017",0,false));
        list.add(new CustomModel("28 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,true));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,true));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("23 May, 2017",0,true));
        list.add(new CustomModel("23 May, 2017",0,false));
        list.add(new CustomModel("23 May, 2017",0,false));
        list.add(new CustomModel("24 May, 2017",0,true));
        list.add(new CustomModel("24 May, 2017",0,false));
        list.add(new CustomModel("28 May, 2017",0,true));
        list.add(new CustomModel("28 May, 2017",0,false));
        list.add(new CustomModel("28 May, 2017",0,false));
        list.add(new CustomModel("28 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,true));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,true));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("23 May, 2017",0,true));
        list.add(new CustomModel("23 May, 2017",0,false));
        list.add(new CustomModel("23 May, 2017",0,false));
        list.add(new CustomModel("24 May, 2017",0,true));
        list.add(new CustomModel("24 May, 2017",0,false));
        list.add(new CustomModel("28 May, 2017",0,true));
        list.add(new CustomModel("28 May, 2017",0,false));
        list.add(new CustomModel("28 May, 2017",0,false));
        list.add(new CustomModel("28 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,true));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,true));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("23 May, 2017",0,true));
        list.add(new CustomModel("23 May, 2017",0,false));
        list.add(new CustomModel("23 May, 2017",0,false));
        list.add(new CustomModel("24 May, 2017",0,true));
        list.add(new CustomModel("24 May, 2017",0,false));
        list.add(new CustomModel("28 May, 2017",0,true));
        list.add(new CustomModel("28 May, 2017",0,false));
        list.add(new CustomModel("28 May, 2017",0,false));
        list.add(new CustomModel("28 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,true));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("21 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,true));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("22 May, 2017",0,false));
        list.add(new CustomModel("23 May, 2017",0,true));
        list.add(new CustomModel("23 May, 2017",0,false));
        list.add(new CustomModel("23 May, 2017",0,false));
        list.add(new CustomModel("24 May, 2017",0,true));
        list.add(new CustomModel("24 May, 2017",0,false));



        return list;
    }
}
