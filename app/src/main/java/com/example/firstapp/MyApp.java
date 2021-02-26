package com.example.firstapp;

import android.app.Application;

import com.example.firstapp.di.components.ContactManagerComponent;

import com.example.firstapp.di.components.DaggerContactManagerComponent;
import com.example.firstapp.di.modules.ContactManagerModule;

public class MyApp extends Application {

    private static ContactManagerComponent contactManagerComponent;
    @Override
    public void onCreate() {
        super.onCreate();

        contactManagerComponent = DaggerContactManagerComponent.builder()
                .contactManagerModule(new ContactManagerModule(this)).build();
    }

    public static ContactManagerComponent getContactManagerComponent() {
        return contactManagerComponent;
    }
}

