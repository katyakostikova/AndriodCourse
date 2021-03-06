package com.example.firstapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.firstapp.models.Contact;

import java.util.List;

import javax.inject.Inject;


public class AllContactsRecyclerViewAdapter extends RecyclerView.Adapter<AllContactsRecyclerViewAdapter.ContactViewHolder> {

    private final List<Contact> contacts;
    private final AllContactsInfoFragmentHelper allContactsInfoFragmentHelper;
    private final boolean openInNewActivity;


    public AllContactsRecyclerViewAdapter(List contacts, AllContactsInfoFragmentHelper allContactsInfoFragmentHelper, boolean openInNewActivity) {
        notifyDataSetChanged();
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
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }


    public class ContactViewHolder extends RecyclerView.ViewHolder {
        protected TextView nameTextView;
        protected TextView numberTextView;

        @Inject
        public ContactManager contactManager;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameContactInfo);
            numberTextView = itemView.findViewById(R.id.numberContactInfo);
        }

        public void bind(final int position) {
            MyApp.getContactManagerComponent().inject(this);
            Contact contact = contactManager.getById(contacts.get(position).getId());
            nameTextView.setText(contact.getContactName());
            numberTextView.setText(contact.getPhoneNumber());

            itemView.setOnClickListener(v -> allContactsInfoFragmentHelper.showContact(contacts.get(position).getId()));
        }
    }
}
