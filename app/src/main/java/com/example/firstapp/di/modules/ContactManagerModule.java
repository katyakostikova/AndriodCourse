package com.example.firstapp.di.modules;

import android.content.Context;

import androidx.room.Room;

import com.example.firstapp.ContactManager;
import com.example.firstapp.room.AppDatabase;
import com.example.firstapp.room.ContactDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ContactManagerModule {

    private Context context;

    public ContactManagerModule(Context context) {
        this.context = context.getApplicationContext();
    }

    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }

    @Singleton
    @Provides
    ContactDao provideContactDao() {
        AppDatabase appDatabase = Room.databaseBuilder(context, AppDatabase.class,
                "contacts_app").allowMainThreadQueries().build();
        return appDatabase.contactDao();
    }
    @Singleton
    @Provides
    ContactManager provideContactManager() {
        return new ContactManager();
    }


}
