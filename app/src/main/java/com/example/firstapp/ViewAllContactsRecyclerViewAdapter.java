package com.example.firstapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.models.Contact;

import java.util.ArrayList;


public class ViewAllContactsRecyclerViewAdapter extends RecyclerView.Adapter<ViewAllContactsRecyclerViewAdapter.ContactViewHolder> {

    private final ArrayList<Contact> contacts;
    private final AllContactsInfoFragmentHelper allContactsInfoFragmentHelper;
    private final boolean openInNewActivity;

    public ViewAllContactsRecyclerViewAdapter(ArrayList contacts, AllContactsInfoFragmentHelper allContactsInfoFragmentHelper, boolean openInNewActivity) {
        this.contacts = contacts;
        this.allContactsInfoFragmentHelper = allContactsInfoFragmentHelper;
        this.openInNewActivity =openInNewActivity;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contact = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_information, parent, false);
        ContactViewHolder contactViewHolder = new ContactViewHolder(contact);
        return contactViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.bind(contacts.get(position));
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView nameTextView;
        protected TextView numberTextView;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameContactInfo);
            numberTextView = itemView.findViewById(R.id.numberContactInfo);
        }

        public void bind(final Contact contact) {
            nameTextView.setText(contact.contactName);
            numberTextView.setText(contact.phoneNumber);

            itemView.setOnClickListener(v -> allContactsInfoFragmentHelper.showContact(contact));
        }
    }
}
