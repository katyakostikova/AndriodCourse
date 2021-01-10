package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<Contact> contacts = new ArrayList<>();
        TextView numberOfContacts = findViewById(R.id.numberOfContacts);
        numberOfContacts.setText("Number of contacts: " + contacts.size());

        Button addButton = findViewById(R.id.buttonAdd);

        View.OnClickListener onAddButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.errorName).setVisibility(View.INVISIBLE);
                findViewById(R.id.errorNumber).setVisibility(View.INVISIBLE);
                findViewById(R.id.nameAlreadyExists).setVisibility(View.INVISIBLE);
                EditText nameInput = (EditText)findViewById(R.id.nameInput);
                String name = nameInput.getText().toString().trim();
                if (name.isEmpty()) {
                    findViewById(R.id.errorName).setVisibility(View.VISIBLE);
                    return;
                }
                for (int i = 0; i < contacts.size(); i++) {
                    if (contacts.get(i).contactName.equals(name)) {
                        findViewById(R.id.nameAlreadyExists).setVisibility(View.VISIBLE);
                        return;
                    }
                }
                EditText numberInput = (EditText)findViewById(R.id.phoneInput);
                String number = numberInput.getText().toString();
                String regex = "(\\+\\d{12})";
                Pattern numberCheck = Pattern.compile(regex);
                Matcher numberRegexCheck = numberCheck.matcher(number.trim());
                if (!numberRegexCheck.matches()) {
                    Log.e("matcher", Boolean.toString(numberRegexCheck.matches()));
                    findViewById(R.id.errorNumber).setVisibility(View.VISIBLE);
                    return;
                }

                contacts.add(new Contact(name, number.trim()));
                numberOfContacts.setText("Number of contacts: " + contacts.size());
                nameInput.setText("");
                numberInput.setText("");
            }
        };

        Button findButton = findViewById(R.id.buttonFind);

        View.OnClickListener onFindButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findViewById(R.id.errorName).setVisibility(View.INVISIBLE);
                findViewById(R.id.errorNumber).setVisibility(View.INVISIBLE);
                findViewById(R.id.nameAlreadyExists).setVisibility(View.INVISIBLE);
                EditText nameInput = (EditText)findViewById(R.id.nameInput);
                String name = nameInput.getText().toString();
                if (name.isEmpty()) {
                    findViewById(R.id.errorName).setVisibility(View.VISIBLE);
                    return;
                }

                EditText numberInput = (EditText)findViewById(R.id.phoneInput);
                for (int i = 0; i < contacts.size(); i++) {
                    if (contacts.get(i).contactName.equals(name)) {
                        numberInput.setText(contacts.get(i).phoneNumber);
                        return;
                    }
                }
                nameInput.setText("Not found");
                numberInput.setText("");
            }
        };

        addButton.setOnClickListener(onAddButtonClickListener);
        findButton.setOnClickListener(onFindButtonClickListener);
    }
}
