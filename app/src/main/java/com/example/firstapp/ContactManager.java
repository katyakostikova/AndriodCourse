package com.example.firstapp;

import com.example.firstapp.models.Contact;

import java.util.ArrayList;

public final class ContactManager implements IContactManager {
    
    private static ContactManager instance = null;
    private ArrayList<Contact> contacts = new ArrayList<>();
    
    private ContactManager() {
        
    }
    
    public static ContactManager getInstance() {
        if (instance == null) {
            instance = new ContactManager();
        }

        return instance;
    }

    @Override
    public Contact get(int position) {
        return contacts.get(position);
    }

    @Override
    public int size() {
        return contacts.size();
    }

    @Override
    public void add(Contact newContact) {
        contacts.add(newContact);
    }
}