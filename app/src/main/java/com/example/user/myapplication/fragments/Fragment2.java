package com.example.user.myapplication.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.activities.FragmentsActivity;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 12/7/17.
 */

public class Fragment2 extends Fragment implements View.OnClickListener {

    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_game)
    TextView tvGame;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    FragmentsActivity activity;
    Fragment1 fragment1;
    private Bundle bundle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_2,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
        btnSubmit.setOnClickListener(this);
        activity= (FragmentsActivity) getActivity();
        String name=bundle.getString("name");
        String game=bundle.getString("game");

        tvGame.setText(game);
        tvName.setText(name);

    }


    @Override
    public void onClick(View v) {
        FragmentManager manager=  activity.getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();

        if (fragment1==null)
        {
            fragment1=new Fragment1();
            transaction.add(fragment1,Fragment1.class.getName());
            transaction.addToBackStack(Fragment1.class.getName());
            transaction.addToBackStack(Fragment1.class.getName());
            transaction.commit();
        }
        else
        {
            manager.popBackStack(Fragment1.class.getName(), Integer.parseInt(null));
        }
    }

    public void getData(Bundle bundle)
    {
        this.bundle=bundle;
    }
}
