package com.example.firstapp;

import com.example.firstapp.models.Contact;
import com.example.firstapp.room.ContactDao;

import java.util.List;

public final class ContactManager implements IContactManager {
    
    private static ContactManager instance = null;
    private final ContactDao contactDao;
    
    private ContactManager(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    public static void init(ContactDao contactDao) {
        if (instance == null) {
            instance = new ContactManager(contactDao);
        }
    }
    
    public static ContactManager getInstance() {
       return instance;
    }

    @Override
    public List<Contact> getAll() {
        return contactDao.getAll();
    }

    @Override
    public Contact getById(int position) {
        return contactDao.getById(position+1);
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
        return contactDao.getAllFavourite(true);
    }

    @Override
    public void delete(Contact contact) {
        contactDao.delete(contact);
    }

    @Override
    public void update(Contact contact) {
        contactDao.update(contact);
    }
}
