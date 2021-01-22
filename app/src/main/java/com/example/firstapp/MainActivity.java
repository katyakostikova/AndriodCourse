package com.example.firstapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    private RecyclerView allContactsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        allContactsRecycler = findViewById(R.id.recyclerViewAllContacts);
        ViewAllContactsRecyclerViewAdapter viewAllContactsRecyclerViewAdapter = new ViewAllContactsRecyclerViewAdapter(ContactManager.getInstance());
        allContactsRecycler.setAdapter(viewAllContactsRecyclerViewAdapter);
        allContactsRecycler.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton addButton = findViewById(R.id.floatingActionButtonAdd);

        View.OnClickListener onAddButtonClickListener = v -> {
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            MainActivity.this.startActivity(intent);
        };

        addButton.setOnClickListener(onAddButtonClickListener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        allContactsRecycler.getAdapter().notifyDataSetChanged();
    }
}
