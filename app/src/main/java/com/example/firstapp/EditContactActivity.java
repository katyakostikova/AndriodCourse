package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.firstapp.models.Contact;

import javax.inject.Inject;

public class EditContactActivity extends AppCompatActivity {

    private int contactId;

    @Inject
    ContactManager contactManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        MyApp.getContactManagerComponent().inject(this);

        Intent intent = getIntent();
        contactId = intent.getExtras().getInt("contactId");

        Contact contact = contactManager.getById(contactId);

        EditText nameEditText = findViewById(R.id.nameInputEdit);
        EditText numberEditText = findViewById(R.id.phoneInputEdit);
        CheckBox favouriteCheckBox = findViewById(R.id.checkBoxFavouriteEdit);

        nameEditText.setText(contact.getContactName());
        numberEditText.setText(contact.getPhoneNumber());
        favouriteCheckBox.setChecked(contact.isFavourite());

        Button saveButton = findViewById(R.id.buttonSaveEdit);

        View.OnClickListener onSaveButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                ContactChecker contactChecker = new ContactChecker();
                if (!contactChecker.isNameInputCorrect(name)) {
                    Toast.makeText(EditContactActivity.this, R.string.wrong_name, Toast.LENGTH_SHORT).show();
                    return;
                }
                String number = numberEditText.getText().toString();
                if (!contactChecker.isNumberInputCorrect(number)) {
                    Toast.makeText(EditContactActivity.this, R.string.wrong_number, Toast.LENGTH_SHORT).show();
                    return;
                }

                boolean isFavourite = favouriteCheckBox.isChecked();
                contact.setContactName(name);
                contact.setPhoneNumber(number);
                contact.setFavourite(isFavourite);

                contactManager.update(contact);
                finish();
                contactManager.notifyDataWasChanged();
                Toast.makeText(EditContactActivity.this, R.string.contact_updated, Toast.LENGTH_LONG).show();
            }
        };

        Button backButton = findViewById(R.id.buttonBackEdit);

        View.OnClickListener onBackButtonClickListener = v -> finish();

        saveButton.setOnClickListener(onSaveButtonClickListener);
        backButton.setOnClickListener(onBackButtonClickListener);
    }
}