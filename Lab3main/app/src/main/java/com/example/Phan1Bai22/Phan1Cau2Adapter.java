package com.example.Phan1Bai22;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.lab3main.R;

import java.util.List;

public class Phan1Cau2Adapter extends ArrayAdapter<Contact> {
    public Phan1Cau2Adapter(Context context, List<Contact> contacts) {
        super(context, 0, contacts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.phan1bai22_listview_item, parent, false);
        }

        Contact contact = getItem(position);

        TextView nameTextView = (TextView) convertView.findViewById(R.id.name);
        TextView phoneNumberTextView = (TextView) convertView.findViewById(R.id.phone_number);

        nameTextView.setText(contact.getName());
        phoneNumberTextView.setText(contact.getPhoneNumber());

        return convertView;
    }
}