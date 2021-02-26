package com.example.firstapp.di.components;


import com.example.firstapp.AddContactActivity;
import com.example.firstapp.AllContactsFragment;
import com.example.firstapp.AllContactsRecyclerViewAdapter;
import com.example.firstapp.ContactChecker;
import com.example.firstapp.ContactInfoFragment;
import com.example.firstapp.ContactManager;
import com.example.firstapp.EditContactActivity;
import com.example.firstapp.MainActivity;
import com.example.firstapp.di.modules.ContactManagerModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ContactManagerModule.class})
public interface ContactManagerComponent {

    void inject(ContactManager obj);
    void inject(ContactChecker obj);
    void inject(AddContactActivity obj);
    void inject(AllContactsFragment obj);
    void inject(AllContactsRecyclerViewAdapter.ContactViewHolder obj);
    void inject(ContactInfoFragment obj);
    void inject(EditContactActivity obj);
    void inject(MainActivity obj);
}
