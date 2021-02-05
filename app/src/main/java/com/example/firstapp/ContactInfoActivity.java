package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

public class ContactInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_information);

        Intent intent =  getIntent();

        Bundle bundle = new Bundle();
        bundle.putInt("contactId", intent.getExtras().getInt("contactId"));

        ContactInfoFragment contactInfoFragment = new ContactInfoFragment();
        contactInfoFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.allContactsInfoRoot, contactInfoFragment).commit();
    }
}