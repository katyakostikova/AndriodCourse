package com.example.firstapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.firstapp.models.Contact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ContactInfoFragment extends Fragment {
    private int contactId;

    public ContactInfoFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_information_fragment, container, false);
        contactId = getArguments().getInt("contactId");
        TextView contactNameTextView =  view.findViewById(R.id.nameFound);
        Contact contact = ContactManager.getInstance().getById(contactId);
        contactNameTextView.setText(contact.getContactName());
        TextView contactNumberTextView =  view.findViewById(R.id.numberFound);
        contactNumberTextView.setText(contact.getPhoneNumber());
        CheckBox  favouriteCheckBox = view.findViewById(R.id.checkBoxFavourite);
        favouriteCheckBox.setChecked(contact.isFavourite());

        FloatingActionButton editButton = view.findViewById(R.id.floatingActionButtonEdit);

        View.OnClickListener onEditButtonClickListener = v -> {
            Intent intent = new Intent(getActivity(), EditContactActivity.class);
            intent.putExtra("contactId", contactId);
            getActivity().startActivity(intent);
            getActivity().onBackPressed();
        };

        FloatingActionButton deleteButton = view.findViewById(R.id.floatingActionButtonDelete);

        View.OnClickListener onDeleteButtonClickListener = v -> {
            ContactManager.getInstance().delete(ContactManager.getInstance().getById(contactId));
            Toast.makeText(getContext(), R.string.contact_deleted, Toast.LENGTH_LONG).show();
            getActivity().onBackPressed();
            ContactManager.getInstance().notifyDataWasChanged();
        };

        ContactManager.getInstance().addOnContactsWasChangedListener(new ContactManager.OnContactsWasChanged() {
            @Override
            public void dataWasChanged() {
                Contact updatedContact = ContactManager.getInstance().getById(contactId);
                if(contact != null) {
                    contactNameTextView.setText(updatedContact.getContactName());
                    contactNumberTextView.setText(updatedContact.getPhoneNumber());
                    favouriteCheckBox.setChecked(updatedContact.isFavourite());
                }

            }
        });

        Button backButton = view.findViewById(R.id.buttonBackToMain);

        View.OnClickListener onBackButtonClickListener = v -> {
            getActivity().onBackPressed();
        };

        Button callButton = view.findViewById(R.id.buttonCall);

        View.OnClickListener onCallButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + contact.getPhoneNumber()));
                if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        };


        callButton.setOnClickListener(onCallButtonClickListener);
        backButton.setOnClickListener(onBackButtonClickListener);
        editButton.setOnClickListener(onEditButtonClickListener);
        deleteButton.setOnClickListener(onDeleteButtonClickListener);
        return view;
    }
}
