package com.example.firstapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.firstapp.models.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements AllContactsInfoFragmentHelper {

    private RecyclerView allContactsRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean openInNewActivity = (findViewById(R.id.allContactsInfoRoot) == null);

        allContactsRecycler = findViewById(R.id.recyclerViewAllContacts);
        ViewAllContactsRecyclerViewAdapter viewAllContactsRecyclerViewAdapter = new ViewAllContactsRecyclerViewAdapter(ContactManager.getInstance(), this, openInNewActivity);
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


    @Override
    public void showContact(Contact contact) {
        Bundle bundle = new Bundle();
        bundle.putString("contactName", contact.contactName);
        bundle.putString("contactNumber", contact.phoneNumber);

        AllContactsInfoFragment allContactsInfoFragment = new AllContactsInfoFragment();
        allContactsInfoFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.allContactsInfoRoot, allContactsInfoFragment).commit();
    }
}

interface AllContactsInfoFragmentHelper {

    void showContact(Contact contact);
}