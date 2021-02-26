package com.example.firstapp;


import com.example.firstapp.models.Contact;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

public class ContactChecker {

    @Inject
    public ContactManager contactManager;

    public boolean isNameInputCorrect(String name) {
        if (name.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean isNameAlreadyExists(String name) {
        MyApp.getContactManagerComponent().inject(this);

        List<Contact> contacts = contactManager.getAll();
        for (Contact c : contacts) {
            if (c.getContactName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean isNumberInputCorrect(String number) {
        String regex = "(\\+\\d{12})";
        Pattern numberCheck = Pattern.compile(regex);
        Matcher numberRegexCheck = numberCheck.matcher(number.trim());
        if (!numberRegexCheck.matches()) {
            return false;
        }
        return true;
    }
}