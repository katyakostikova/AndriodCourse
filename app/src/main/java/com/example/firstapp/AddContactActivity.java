package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstapp.models.Contact;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class AddContactActivity extends AppCompatActivity {

    @Inject
    public ContactManager contactManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        MyApp.getContactManagerComponent().inject(this);

        Button addButton = findViewById(R.id.buttonSaveEdit);

        View.OnClickListener onAddButtonClickListener = v -> {

            EditText nameInput = findViewById(R.id.nameInputEdit);
            String name = nameInput.getText().toString();
            ContactChecker contactChecker = new ContactChecker();
            if (!contactChecker.isNameInputCorrect(name)) {
                Toast.makeText(AddContactActivity.this, R.string.wrong_name, Toast.LENGTH_SHORT).show();
                return;
            }
            if (!contactChecker.isNameAlreadyExists(name)) {
                Toast.makeText(AddContactActivity.this, R.string.toast_same_name, Toast.LENGTH_SHORT).show();
                return;
            }

            EditText numberInput = findViewById(R.id.phoneInputEdit);
            String number = numberInput.getText().toString();
            if (!contactChecker.isNumberInputCorrect(number)) {
                Toast.makeText(AddContactActivity.this, R.string.wrong_number, Toast.LENGTH_SHORT).show();
                return;
            }

            CheckBox checkBoxFavourite = findViewById(R.id.checkBoxFavouriteEdit);
            boolean isFavourite = checkBoxFavourite.isChecked();

            contactManager.add(new Contact(name.trim(), number, isFavourite));
            AddContactActivity.this.finish();
            contactManager.notifyDataWasChanged();
        };

        Button backButton = findViewById(R.id.buttonBackEdit);

        View.OnClickListener onBackButtonClickListener = v -> finish();

        addButton.setOnClickListener(onAddButtonClickListener);
        backButton.setOnClickListener(onBackButtonClickListener);
    }
}