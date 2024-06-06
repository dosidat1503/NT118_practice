package com.example.Phan1Bai23;
public class Student {
    private int mssv;
    private String name;
    private String lop;

    public Student() {}

    public Student(int mssv, String name, String lop) {
        this.mssv = mssv;
        this.name = name;
        this.lop = lop;
    }

    public int getMssv() {
        return mssv;
    }

    public void setMssv(int mssv) {
        this.mssv = mssv;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }
}
