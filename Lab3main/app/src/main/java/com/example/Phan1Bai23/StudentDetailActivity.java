package com.example.Phan1Bai23;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab3main.R;

public class StudentDetailActivity  extends AppCompatActivity {
    private EditText nameEditText, ageEditText;
    private Button saveButton, deleteButton;
    private DatabaseHelper db;
    private Student student;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phan1bai23_activity_student_detail);

        nameEditText = findViewById(R.id.name);
        ageEditText = findViewById(R.id.age);
        saveButton = findViewById(R.id.save);
        deleteButton = findViewById(R.id.delete);
        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        int studentId = intent.getIntExtra("studentId", -1);

        if (studentId != -1) {
            student = db.getStudent(studentId);
            nameEditText.setText(student.getName());
//            ageEditText.setText(String.valueOf(student.getAge()));
            deleteButton.setVisibility(View.VISIBLE);
        } else {
            student = new Student();
        }

        saveButton.setOnClickListener(v -> {
            student.setName(nameEditText.getText().toString());
//            student.setAge(Integer.parseInt(ageEditText.getText().toString()));

            if (studentId == -1) {
                db.addStudent(student);
            } else {
                db.updateStudent(student);
            }

            finish();
        });

        deleteButton.setOnClickListener(v -> {
            db.deleteStudent(student);
            finish();
        });
    }
}
