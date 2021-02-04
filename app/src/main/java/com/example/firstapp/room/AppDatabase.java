package com.example.firstapp.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.firstapp.models.Contact;

@Database(entities = {Contact.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ContactDao contactDao();

}
