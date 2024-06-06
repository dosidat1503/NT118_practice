package com.example.lab3main;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Phan1Bai1Activity extends AppCompatActivity{

    private DbAdapter dbAdapter;
    private Cursor cursor;
    private List<String> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phan1bai1);
        dbAdapter = new DbAdapter(this);
        dbAdapter.open();
        dbAdapter.deleteAllUsers();
        for (int i = 0; i < 10; i++) {
            dbAdapter.createUser("Nguyễn Văn An " + i);
        }
        users = getData();
        showData();
    }

    private List<String> getData() {
        List<String> users = new ArrayList<>();
        cursor = dbAdapter.getAllUsers();
        if (cursor != null && cursor.getCount() > 0) {
            int columnIndex = cursor.getColumnIndex(DbAdapter.KEY_NAME);
            if (columnIndex != -1) {
                while (cursor.moveToNext()) {
                    users.add(cursor.getString(columnIndex));
                }
            } else {
                // Handle the case where the column does not exist
                // This could be logging an error, throwing an exception, etc.
            }
        } else {
            cursor = null; // hoặc gán cursor bằng giá trị khác như 0 tùy theo yêu cầu của bạn
        }
        return users;
    }
    private void showData() {
        ListView lvUser = (ListView) findViewById(R.id.lv_user);
        ArrayAdapter<String> userAdapter = new
                ArrayAdapter<String>(Phan1Bai1Activity.this, R.layout.phan1bai1_item_user, users);
        lvUser.setAdapter(userAdapter);
    }
}
