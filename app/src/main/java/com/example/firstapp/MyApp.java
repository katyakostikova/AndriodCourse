package com.example.firstapp;

import android.app.Application;

import androidx.room.Room;

import com.example.firstapp.room.AppDatabase;
import com.example.firstapp.room.ContactDao;

public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AppDatabase appDatabase = Room.databaseBuilder(getApplicationContext(), AppDatabase.class,
                "contacts_app").allowMainThreadQueries().build();
        ContactDao contactDao = appDatabase.contactDao();

        ContactManager.init(contactDao);
    }
}

