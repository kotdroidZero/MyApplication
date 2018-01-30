package com.example.user.myapplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.ListPopupWindow;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.myapplication.R;
import com.example.user.myapplication.activities.AddContactActivity;
import com.example.user.myapplication.activities.ContactLoaderActivity;
import com.example.user.myapplication.database.DBHelper;
import com.example.user.myapplication.helpers.Constants;
import com.example.user.myapplication.pojo.PhoneBook;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by user on 19/7/17.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.RecyclerViewHolder>{
    List<PhoneBook> list;
    Context context;

    public ContactAdapter(List<PhoneBook> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new RecyclerViewHolder(LayoutInflater.from(context).inflate(R.layout.phonebook_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {
      //  holder.tvLastContacted.setText(list.get(position).getLastContacted());
        holder.tvName.setText(list.get(position).getName());
        holder.tvPhoneNumber.setText(list.get(position).getPhoneNumber());
        Log.e("Uid of "+list.get(position).getName(),list.get(position).getUid()+"");

        if (list.get(position).getProfile()!=null)
        {
            Uri profile=Uri.parse(list.get(position).getProfile());
            Picasso.with(context).load(profile).resize(50,50).into(holder.ivProfile);
        }
        else
        {
            holder.ivProfile.setImageResource(R.mipmap.ic_launcher_round);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addContact(PhoneBook model) {
        list.add(0,model);
        notifyDataSetChanged();
    }

    public void addContactList(List<PhoneBook> list1) {
        list.clear();
        list.addAll(0,list1);
        notifyDataSetChanged();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_profile)
        ImageView ivProfile;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_phonenumber)
        TextView tvPhoneNumber;
        @BindView(R.id.tv_lastcontacted)
        TextView tvLastContacted;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    openPopupWindow(v);
                    return true;
                }
            });
        }
        private void deleteContact() {
            DBHelper helper=new DBHelper(context);
            PhoneBook model=list.get(getAdapterPosition());
            helper.deleteContactRow(model);
        }

        private void updateContact() {

            Bundle bundle=new Bundle();
            bundle.putSerializable("model",list.get(getAdapterPosition()));
            Intent intentModify=new Intent(context, AddContactActivity.class);
            intentModify.putExtras(bundle);
            ((ContactLoaderActivity)context).startActivityForResult(intentModify, Constants.CONTACT_ADD_REQUEST);
        }
        private void openPopupWindow(View v) {
            String[] options=new String[]{"Modify","Delete"};
            ArrayAdapter<String> adapter=new ArrayAdapter<>(context,android.R.layout.simple_list_item_1,android.R.id.text1,options);
            final ListPopupWindow popupWindow=new ListPopupWindow(context);
            popupWindow.setAdapter(adapter);
            popupWindow.setModal(true);
            popupWindow.setAnchorView(v);

            popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    switch (position)
                    {
                        case 0:
                            updateContact();
                            popupWindow.dismiss();
                            break;
                        case 1:
                            deleteContact();
                            list.remove(getAdapterPosition());
                            notifyDataSetChanged();
                            popupWindow.dismiss();
                            break;
                    }

                }});
            popupWindow.show();
        }
    }
}
