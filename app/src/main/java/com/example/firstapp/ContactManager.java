package com.example.firstapp;

import com.example.firstapp.models.Contact;
import com.example.firstapp.room.ContactDao;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean isNameInputCorrect(String name) {
        if (name.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean isNameAlreadyExists(String name) {
        List<Contact> contacts = getInstance().getAll();
        for (Contact c : contacts) {
            if (c.getContactName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumberInputCorrect(String number) {
        String regex = "(\\+\\d{12})";
        Pattern numberCheck = Pattern.compile(regex);
        Matcher numberRegexCheck = numberCheck.matcher(number.trim());
        if (!numberRegexCheck.matches()) {
            return false;
        }
        return true;
    }
}
