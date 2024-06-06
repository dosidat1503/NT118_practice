package com.example.lab3main;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Phan1Bai23.DatabaseHelper;
import com.example.Phan1Bai23.Student;
import com.example.Phan1Bai23.StudentAdapter;
import com.example.Phan1Bai23.StudentDetailActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Phan1Bai23Activity extends AppCompatActivity implements StudentAdapter.OnStudentListener{
    private EditText edtMSSV, edtHoTen, edtLop;
    private Button btnInsert, btnDelete, btnUpdate, btnQuery;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private List<Student> studentList = new ArrayList<>();
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phan1bai23_activity_main);

        db = new DatabaseHelper(this);
        edtMSSV = findViewById(R.id.edtMSSV);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtLop = findViewById(R.id.edtLop);
        btnInsert = findViewById(R.id.btnInsert);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnQuery = findViewById(R.id.btnQuery);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        studentAdapter = new StudentAdapter(this, studentList, this);
        recyclerView.setAdapter(studentAdapter);

        btnInsert.setOnClickListener(v -> {
            Student student = new Student(Integer.parseInt(edtMSSV.getText().toString()), edtHoTen.getText().toString(), edtLop.getText().toString());
            db.addStudent(student);
            refreshStudentList();
        });

        btnDelete.setOnClickListener(v -> {
            int mssv = Integer.parseInt(edtMSSV.getText().toString());
            Student student = db.getStudent(mssv);
            db.deleteStudent(student);
            refreshStudentList();
        });

        btnUpdate.setOnClickListener(v -> {
            Student student = new Student(Integer.parseInt(edtMSSV.getText().toString()), edtHoTen.getText().toString(), edtLop.getText().toString());
            db.updateStudent(student);
            refreshStudentList();
        });

        btnQuery.setOnClickListener(v -> {
            refreshStudentList();
        });

        refreshStudentList();
    }

    private void refreshStudentList() {
        studentList.clear();
        studentList.addAll(db.getAllStudents());
        studentAdapter.notifyDataSetChanged();
    }

    @Override
    public void onStudentClick(int position) {
        Student student = studentList.get(position);
        edtMSSV.setText(String.valueOf(student.getMssv()));
        edtHoTen.setText(student.getName());
        edtLop.setText(student.getLop());
    }
}
