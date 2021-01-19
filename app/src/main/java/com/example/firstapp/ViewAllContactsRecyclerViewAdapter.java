package com.example.firstapp;

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

    public ViewAllContactsRecyclerViewAdapter(ArrayList<Contact> contacts) {
        this.contacts = contacts;
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
        holder.nameTextView.setText(contacts.get(position).contactName);
        holder.numberTextView.setText(contacts.get(position).phoneNumber);
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
    }
}
