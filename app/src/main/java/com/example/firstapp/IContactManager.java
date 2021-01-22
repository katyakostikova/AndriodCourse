package com.example.firstapp;

import com.example.firstapp.models.Contact;

public interface IContactManager {

    Contact get(int position);
    int size();
    void add(Contact newContact);

}
