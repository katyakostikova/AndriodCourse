package com.example.firstapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.models.Contact;


public class ViewAllContactsRecyclerViewAdapter extends RecyclerView.Adapter<ViewAllContactsRecyclerViewAdapter.ContactViewHolder> {

    private final ContactManager contacts;

    public ViewAllContactsRecyclerViewAdapter(ContactManager contacts) {
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
            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(nameTextView.getContext(), InfoContactActivity.class);
                intent.putExtra("contactName", contact.contactName);
                intent.putExtra("contactNumber", contact.phoneNumber);
                nameTextView.getContext().startActivity(intent);
            });
        }
    }
}
