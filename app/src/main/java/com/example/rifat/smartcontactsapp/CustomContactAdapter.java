package com.example.rifat.smartcontactsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rifat on 4/1/2015.
 */
public class CustomContactAdapter extends ArrayAdapter<MyContact> {

    LayoutInflater inflater;

    public CustomContactAdapter(Context context, List<MyContact> objects) {
        super(context, 0, objects);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.contacts_list_item, null);

            holder = new ViewHolder();
            holder.ivContact = (ImageView) convertView.findViewById(R.id.ivContact);
            holder.tvContactName = (TextView) convertView.findViewById(R.id.tvContactName);
//            holder.tvContactNumber = (TextView) convertView.findViewById(R.id.tvContactNumber);
//            holder.tvEmailAddress = (TextView) convertView.findViewById(R.id.tvEmailAddress);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        MyContact contact = getItem(position);
        holder.tvContactName.setText(contact.getName());
        holder.ivContact.setImageResource(R.drawable.ic_launcher);
//        holder.tvContactNumber.setText(contact.getPhoneNumbers());
//        holder.tvEmailAddress.setText(contact.getEmailIds());

        return convertView;
    }

    static class ViewHolder {
        public ImageView ivContact;
        public TextView tvContactName;
//        public TextView tvContactNumber;
//        public TextView tvEmailAddress;
    }
}
