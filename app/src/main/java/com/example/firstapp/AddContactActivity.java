package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstapp.models.Contact;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Button addButton = findViewById(R.id.buttonAdd2);

        View.OnClickListener onAddButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactManager contactManager = ContactManager.getInstance();

                EditText nameInput = findViewById(R.id.nameInput2);
                String name = nameInput.getText().toString();
                if (name.isEmpty()) {
                    Toast.makeText(AddContactActivity.this, "Wrong name", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (int i = 0; i < contactManager.size(); i++) {
                    if (contactManager.get(i).contactName.equals(name)) {
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

                CheckBox checkBoxFavourite = findViewById(R.id.checkBoxFavourite);
                boolean isFavourite = checkBoxFavourite.isChecked();

                contactManager.add(new Contact(name.trim(), number, isFavourite));
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