package com.example.lab3main;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Phan1Bai22.Contact;
import com.example.Phan1Bai22.DatabaseHandler;
import com.example.Phan1Bai22.Phan1Cau2Adapter;

import java.util.List;

public class Phan1Bai22Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phan1bai22_listview);
        DatabaseHandler db = new DatabaseHandler(this);
/**
 * CRUD Operations
 * */
// Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        db.addContact(new Contact("Ravi", "9100000000"));
        db.addContact(new Contact("Srinivas", "9199999999"));
        db.addContact(new Contact("Tommy", "9522222222"));
        db.addContact(new Contact("Karthik", "9533333333"));
// Reading all contacts
        Log.e("Reading: ", "Reading all contacts..");
        List<Contact> contacts = db.getAllContacts();
        for (Contact cn : contacts) {
            String log = "Id: " + cn.getID() + " ,Name: " + cn.getName() + " ,Phone: " + cn.getPhoneNumber();
// Writing Contacts to log
            Log.e("Name: ", log);
        }

        //Hiển thị ra màn hình
        // Tạo ListView trong layout
        ListView listView = findViewById(R.id.listView);

// Tạo Adapter
        ArrayAdapter<Contact> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contacts);
        listView.setAdapter(adapter);

// Đặt Listener cho sự kiện long click
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy contact tại vị trí được click
                Contact contact = (Contact) parent.getItemAtPosition(position);

                // Xóa contact khỏi Database
                db.deleteContact(contact.getContact());

                // Cập nhật ListView
                contacts.remove(position);
                adapter.notifyDataSetChanged();

                return true;
            }
        });

        List<Contact> contacts2 = db.getAllContacts();
        Phan1Cau2Adapter adapter2 = new Phan1Cau2Adapter(this, contacts2);
        ListView listView2 = (ListView) findViewById(R.id.listView);
        listView2.setAdapter(adapter2);
        listView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                // Lấy contact tại vị trí được click
                Contact contact = (Contact) parent.getItemAtPosition(position);

                // Xóa contact khỏi Database
                db.deleteContact(contact.getContact());

                // Cập nhật ListView
                contacts2.remove(position);
                adapter2.notifyDataSetChanged();
                return true;
            }
        });
    }
}
