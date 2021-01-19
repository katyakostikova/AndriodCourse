package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstapp.models.Contact;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddContactActivity extends AppCompatActivity {

    private ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Button addButton = findViewById(R.id.buttonAdd2);
        Intent intent = getIntent();

        View.OnClickListener onAddButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddContactActivity.this.contacts =  new ArrayList<>(intent.getExtras().getParcelableArrayList("contactsList"));

                EditText nameInput = (EditText) findViewById(R.id.nameInput2);
                String name = nameInput.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(AddContactActivity.this, "Wrong name", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i = 0; i < contacts.size(); i++) {
                    if (contacts.get(i).contactName.equals(name)) {
                        Toast.makeText(AddContactActivity.this, "Contact with such name already exists", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                EditText numberInput = findViewById(R.id.phoneInput2);
                String number = numberInput.getText().toString();
                String regex = "(\\+\\d{12})";
                Pattern numberCheck = Pattern.compile(regex);
                Matcher numberRegexCheck = numberCheck.matcher(number.trim());
                if (!numberRegexCheck.matches()) {
                    Toast.makeText(AddContactActivity.this, "Wrong number", Toast.LENGTH_SHORT).show();
                    return;
                }
                intent.putExtra("contactName", name.trim());
                intent.putExtra("phoneNumber", number);
                AddContactActivity.this.setResult(RESULT_OK, intent);
                AddContactActivity.this.finish();
            }
        };

        Button backButton = findViewById(R.id.buttonBack3);

        View.OnClickListener onBackButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };

        addButton.setOnClickListener(onAddButtonClickListener);
        backButton.setOnClickListener(onBackButtonClickListener);
    }
}