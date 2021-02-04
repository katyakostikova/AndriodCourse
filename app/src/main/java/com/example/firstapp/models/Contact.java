package com.example.firstapp.models;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Contact  {
    private String contactName;
    private String phoneNumber;
    private boolean isFavourite;

    @PrimaryKey(autoGenerate = true)
    private int id;

    public Contact(String contactName, String phoneNumber, boolean isFavourite) {
        this.contactName = contactName;
        this.phoneNumber= phoneNumber;
        this.isFavourite = isFavourite;
    }

    public String getContactName() {
        return contactName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public int getId() {
        return id;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }

    public void setId(int id) {
        this.id = id;
    }
}



