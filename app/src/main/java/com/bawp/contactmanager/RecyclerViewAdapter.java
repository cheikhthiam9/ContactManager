package com.bawp.contactmanager;
//DO NOT FORGET TO IMPORT RecycleView.Adapter
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bawp.contactmanager.model.Contact;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private Context context;
    private List<Contact> contactList;

    public RecyclerViewAdapter(Context context, List<Contact> contactList) {
        this.context = context;
        this.contactList = contactList;
    }

    @NonNull
    @Override
    //where we create the view
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Creating a view initializing it to a layout inflator
        //Layout inflator: Parses xml file (the contact row) to an android object

        View view = LayoutInflater.from(this.context).inflate(R.layout.contact_row, viewGroup, false);
        return new ViewHolder(view);
    }


    //where we binding our contact row with the data and the recycle view
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Contact contact = contactList.get(position); // each contact object inside of our list

        //Bind data with viewholder
        viewHolder.contactName.setText(contact.getName());
        viewHolder.phoneNumber.setText(contact.getPhoneNumber());


    }



    @Override
    public int getItemCount() {
        return contactList.size();
    }


//This was created by us
public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView contactName;
        public TextView phoneNumber;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            contactName = itemView.findViewById(R.id.name);
            phoneNumber = itemView.findViewById(R.id.phone);
        }
    }



}
