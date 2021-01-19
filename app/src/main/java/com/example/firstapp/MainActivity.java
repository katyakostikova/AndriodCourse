package com.example.firstapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.firstapp.models.Contact;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView numberOfContacts = findViewById(R.id.numberOfContacts);
        numberOfContacts.setText("Number of contacts: " + contacts.size());

        Button addButton = findViewById(R.id.buttonAdd);

        View.OnClickListener onAddButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
                intent.putExtra("contactsList", contacts);
                MainActivity.this.startActivityForResult(intent, 1);
            }
        };

        Button findButton = findViewById(R.id.buttonFind);

        View.OnClickListener onFindButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameInput = findViewById(R.id.nameInput);
                String name = nameInput.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Wrong name", Toast.LENGTH_SHORT).show();
                    return;
                }

                Contact contact;
                for (int i = 0; i < contacts.size(); i++) {
                    if (contacts.get(i).contactName.equals(name.trim())) {
                        contact = contacts.get(i);
                        Intent intent = new Intent(MainActivity.this, FindContactActivity.class);
                        intent.putExtra("contactName", contact.contactName);
                        intent.putExtra("contactNumber", contact.phoneNumber);
                        MainActivity.this.startActivity(intent);
                        nameInput.setText("");
                        return;
                    }
                }
                Toast.makeText(MainActivity.this, "You don't have such contact in your list", Toast.LENGTH_LONG).show();
            }
        };

        Button viewAllContactsButton = findViewById(R.id.buttonViewAllContacts);

        View.OnClickListener onViewAllContactsButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewAllContactsActivity.class);
                intent.putExtra("contactsList", contacts);
                MainActivity.this.startActivity(intent);
            }
        };

        addButton.setOnClickListener(onAddButtonClickListener);
        findButton.setOnClickListener(onFindButtonClickListener);
        viewAllContactsButton.setOnClickListener(onViewAllContactsButtonClickListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                String name = data.getExtras().getString("contactName");
                String number = data.getExtras().getString("phoneNumber");
                contacts.add(new Contact(name, number.trim()));
                TextView numberOfContacts = findViewById(R.id.numberOfContacts);
                numberOfContacts.setText("Number of contacts: " + contacts.size());
            }
        }
    }
}
