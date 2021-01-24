package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;

public class InfoContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        Intent intent =  getIntent();

        Bundle bundle = new Bundle();
        bundle.putString("contactName", intent.getExtras().getString("contactName"));
        bundle.putString("contactNumber", intent.getExtras().getString("contactNumber"));

        AllContactsInfoFragment allContactsInfoFragment = new AllContactsInfoFragment();
        allContactsInfoFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.allContactsInfoRoot, allContactsInfoFragment).commit();
    }
}