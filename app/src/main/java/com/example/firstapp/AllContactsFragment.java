package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllContactsFragment extends Fragment implements AllContactsInfoFragmentHelper{

    private int tabPosition;
    private RecyclerView allContactsRecycler;

    public AllContactsFragment() {

    }

    public AllContactsFragment(int position) {
        this.tabPosition = position;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.all_contacts_fragment, container, false);
        allContactsRecycler = view.findViewById(R.id.recyclerViewAllContacts);

        boolean openInNewActivity = (view.findViewById(R.id.allContactsInfoRoot) == null);
        allContactsRecycler = view.findViewById(R.id.recyclerViewAllContacts);
        AllContactsRecyclerViewAdapter allContactsRecyclerViewAdapter = new AllContactsRecyclerViewAdapter(ContactManager.getInstance().getAll(),
                this, openInNewActivity);
        if (tabPosition == 1) {
            if (ContactManager.getInstance().size() > 3) {
                int fromIndex = ContactManager.getInstance().size() - 3;
                int toIndex = ContactManager.getInstance().size();
                allContactsRecyclerViewAdapter = new AllContactsRecyclerViewAdapter( ContactManager.getInstance().getAll().subList(fromIndex, toIndex), this, openInNewActivity);
            }
        } else if (tabPosition == 2) {
            allContactsRecyclerViewAdapter = new AllContactsRecyclerViewAdapter(ContactManager.getInstance().getContactFavourites(), this, openInNewActivity);
        }
        allContactsRecycler.setAdapter(allContactsRecyclerViewAdapter);
        allContactsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));

        ContactManager.getInstance().addOnContactsWasChangedListener(new ContactManager.OnContactsWasChanged() {
            @Override
            public void dataWasChanged() {
                allContactsRecycler.getAdapter().notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public void showContact(int contactId) {
        Intent intent = new Intent(getActivity(), ContactInfoActivity.class);
        intent.putExtra("contactId", contactId);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            allContactsRecycler.getAdapter().notifyDataSetChanged();
        } catch (NullPointerException err) {

        }

    }
}

interface AllContactsInfoFragmentHelper {

    void showContact(int position);
}
