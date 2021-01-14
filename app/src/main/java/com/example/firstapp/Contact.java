package com.example.firstapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Contact implements Parcelable {
    public String contactName;
    public String phoneNumber;

    public Contact(String name, String phoneNumber) {
        this.contactName = name;
        this.phoneNumber= phoneNumber;
    }

    protected Contact(Parcel in) {
        contactName = in.readString();
        phoneNumber = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contactName);
        dest.writeString(phoneNumber);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}



