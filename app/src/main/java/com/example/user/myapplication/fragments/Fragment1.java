package com.example.user.myapplication.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.user.myapplication.R;
import com.example.user.myapplication.activities.FragmentsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 12/7/17.
 */

public class Fragment1 extends Fragment implements View.OnClickListener {

    @BindView(R.id.et_game)
    EditText etGame;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
    FragmentsActivity activity;
    Fragment2 fragment2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.frag_1,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);

        btnSubmit.setOnClickListener(this);
        activity= (FragmentsActivity) getActivity();
    }

    @Override
    public void onClick(View v) {

        Bundle bundle=new Bundle();
        String name=etName.getText().toString().trim();
        String game=etGame.getText().toString().trim();
        bundle.putString("name",name);
        bundle.putString("game",game);

        activity.setExtraBundle(bundle);
        FragmentManager manager=  activity.getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();

        if (fragment2==null)
        {
            fragment2=new Fragment2();
            transaction.add(fragment2,Fragment2.class.getName());
            transaction.addToBackStack(Fragment1.class.getName());
            transaction.commit();
        }
        else
        {
            manager.popBackStack(Fragment2.class.getName(), Integer.parseInt(null));
        }

    }
}
