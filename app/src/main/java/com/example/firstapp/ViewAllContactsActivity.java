package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.firstapp.models.Contact;

import java.util.ArrayList;

public class ViewAllContactsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_contacts);

        Intent intent =  getIntent();
        ArrayList<Contact> contacts = intent.getExtras().getParcelableArrayList("contactsList");

        RecyclerView allContactsRecycler = findViewById(R.id.recyclerViewAllContacts);
        ViewAllContactsRecyclerViewAdapter viewAllContactsRecyclerViewAdapter = new ViewAllContactsRecyclerViewAdapter(contacts);
        allContactsRecycler.setAdapter(viewAllContactsRecyclerViewAdapter);
        allContactsRecycler.setLayoutManager(new LinearLayoutManager(this));

        Button backButton = findViewById(R.id.buttonBack3);
        View.OnClickListener onBackButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        backButton.setOnClickListener(onBackButtonClickListener);
    }
}