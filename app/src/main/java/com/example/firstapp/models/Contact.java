package com.example.firstapp.models;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class Contact implements Parcelable {
    public String contactName;
    public String phoneNumber;
    public boolean isFavourite;

    public Contact(String name, String phoneNumber, boolean isFavourite) {
        this.contactName = name;
        this.phoneNumber= phoneNumber;
        this.isFavourite = isFavourite;
    }


    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected Contact(Parcel in) {
        contactName = in.readString();
        phoneNumber = in.readString();
        isFavourite = in.readBoolean();
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contactName);
        dest.writeString(phoneNumber);
        dest.writeBoolean(isFavourite);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Contact> CREATOR = new Creator<Contact>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
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



