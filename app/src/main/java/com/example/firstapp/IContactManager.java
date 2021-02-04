package com.example.firstapp;

import com.example.firstapp.models.Contact;

import java.util.List;

public interface IContactManager {

    Contact getById(int position);
    int size();
    void add(Contact newContact);
    List<Contact> getContactFavourites();
    List<Contact> getAll();
    void delete(Contact contact);
    void update(Contact contact);

}
