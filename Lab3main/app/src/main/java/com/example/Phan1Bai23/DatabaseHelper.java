package com.example.Phan1Bai23;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "studentManager";
    private static final String TABLE_STUDENTS = "students";
    private static final String KEY_MSSV = "mssv";
    private static final String KEY_NAME = "name";
    private static final String KEY_LOP = "lop";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENTS + "("
                + KEY_MSSV + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_LOP + " TEXT" + ")";
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENTS);
        onCreate(db);
    }

    public void addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_MSSV, student.getMssv());
        values.put(KEY_NAME, student.getName());
        values.put(KEY_LOP, student.getLop());
        db.insert(TABLE_STUDENTS, null, values);
        db.close();
    }

    public Student getStudent(int mssv) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENTS, new String[]{KEY_MSSV, KEY_NAME, KEY_LOP}, KEY_MSSV + "=?",
                new String[]{String.valueOf(mssv)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Student student = new Student(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        cursor.close();
        return student;
    }

    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setMssv(Integer.parseInt(cursor.getString(0)));
                student.setName(cursor.getString(1));
                student.setLop(cursor.getString(2));
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return studentList;
    }

    public int updateStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.getName());
        values.put(KEY_LOP, student.getLop());

        return db.update(TABLE_STUDENTS, values, KEY_MSSV + " = ?",
                new String[]{String.valueOf(student.getMssv())});
    }

    public void deleteStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENTS, KEY_MSSV + " = ?",
                new String[]{String.valueOf(student.getMssv())});
        db.close();
    }
}

