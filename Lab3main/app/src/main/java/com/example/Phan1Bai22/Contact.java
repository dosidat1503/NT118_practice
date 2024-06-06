package com.example.Phan1Bai22;

public class Contact {
    private int id;
    private String name;
    private String phoneNumber;

    public Contact(String ravi, String number) {
        this.name = ravi;
        this.phoneNumber = number;
    }
    public Contact() {}

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Contact getContact() {
        return this;
    }
}
