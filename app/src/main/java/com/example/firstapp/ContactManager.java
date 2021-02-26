package com.example.firstapp;

import com.example.firstapp.models.Contact;
import com.example.firstapp.room.ContactDao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public final class ContactManager implements IContactManager {

    @Inject
    public ContactDao contactDao;
    
    public ContactManager() {
        MyApp.getContactManagerComponent().inject(this);
    }

    @Override
    public List<Contact> getAll() {
        return contactDao.getAll();
    }

    @Override
    public Contact getById(int contactId) {
        return contactDao.getById(contactId);
    }

    @Override
    public int size() {
        return contactDao.getAll().size();
    }

    @Override
    public void add(Contact newContact) {
        contactDao.insert(newContact);
    }

    @Override
    public List<Contact> getContactFavourites() {
        return contactDao.getAllFavourite();
    }

    @Override
    public void delete(Contact contact) {
        contactDao.delete(contact);
    }

    @Override
    public void update(Contact contact) {
        contactDao.update(contact);
    }

    private List<OnContactsWasChanged> listeners = new ArrayList<>();

    public void addOnContactsWasChangedListener(OnContactsWasChanged listener) {
        listeners.add(listener);
    }

    public void notifyDataWasChanged() {
        for(OnContactsWasChanged listener : listeners) {
            listener.dataWasChanged();
        }
    }

    interface OnContactsWasChanged {
        void dataWasChanged();
    }

}
