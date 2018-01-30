package com.example.user.myapplication.activities;

import android.graphics.Color;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.user.myapplication.R;
import com.example.user.myapplication.adapter.PaginationAdapter;
import com.example.user.myapplication.pojo.PagingModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaginationExample extends AppCompatActivity {
    PaginationAdapter adapter;

    List<PagingModel> list=new ArrayList<>();
//    @BindView(R.id.recyclerview1)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination_example);
        ButterKnife.bind(this);
        recyclerView= (RecyclerView) findViewById(R.id.recyclerview1);


        //swipeRefreshLayout.setColorSchemeColors(Color.RED,Color.GREEN,Color.BLUE);
        loadInitialData();
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        adapter =new PaginationAdapter(list,this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
       /* swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        addNewDataAgain();
                        adapter.notifyItemInserted(list.size()-1);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },2000);
            }
        });*/

//        for (int i=0;i<5;i++)
//        {

//       }



    }

    public void addNewDataAgain() {
        //if (count < 3) {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    for (int i = 0; i < 10; i++) {
                        list.add(new PagingModel("Pushkar ", "pushkar"+ (list.size() + 1) + "@gmail.com"));
                    }
                    adapter.updateList();
                    Toast.makeText(PaginationExample.this, "Refrteshed", Toast.LENGTH_SHORT).show();

                }
            }, 3000);
           // count++;
        }

    private void loadInitialData()
    {
        for (int i=0;i<10;i++)
        {
            list.add(new PagingModel("Pushkar Srivastava","pushkar"+(list.size()+1)+"@gmail.com"));
        }
    }
}
