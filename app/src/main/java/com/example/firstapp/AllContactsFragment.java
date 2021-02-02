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

import com.example.firstapp.models.Contact;

import java.util.ArrayList;

public class AllContactsFragment extends Fragment implements AllContactsInfoFragmentHelper{

    private final int tabPosition;
    private RecyclerView allContactsRecycler;

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
        View view = inflater.inflate(R.layout.activity_all_contacts_fragment, container, false);
        allContactsRecycler = view.findViewById(R.id.recyclerViewAllContacts);

        boolean openInNewActivity = (view.findViewById(R.id.allContactsInfoRoot) == null);

        allContactsRecycler = view.findViewById(R.id.recyclerViewAllContacts);
        ViewAllContactsRecyclerViewAdapter viewAllContactsRecyclerViewAdapter = new ViewAllContactsRecyclerViewAdapter(ContactManager.getInstance().getContact(), this, openInNewActivity);
        if (tabPosition == 1) {
            try {
                int fromIndex = ContactManager.getInstance().getContact().size() - 3;
                int toIndex = ContactManager.getInstance().getContact().size();
                viewAllContactsRecyclerViewAdapter = new ViewAllContactsRecyclerViewAdapter((ArrayList) ContactManager.getInstance().getContact().subList(fromIndex, toIndex), this, openInNewActivity);
            } catch (Exception err) {
                viewAllContactsRecyclerViewAdapter = new ViewAllContactsRecyclerViewAdapter(ContactManager.getInstance().getContact(), this, openInNewActivity);
            }
        } else if (tabPosition == 2) {
            viewAllContactsRecyclerViewAdapter = new ViewAllContactsRecyclerViewAdapter(ContactManager.getInstance().getContactFavourites(), this, openInNewActivity);
        }
        allContactsRecycler.setAdapter(viewAllContactsRecyclerViewAdapter);
        allContactsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void showContact(Contact contact) {
        if (getActivity().findViewById(R.id.allContactsInfoRoot) == null) {
            Intent intent = new Intent(getActivity(), InfoContactActivity.class);
            intent.putExtra("contactName", contact.contactName);
            intent.putExtra("contactNumber", contact.phoneNumber);
            startActivity(intent);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("contactName", contact.contactName);
            bundle.putString("contactNumber", contact.phoneNumber);

            AllContactsInfoFragment allContactsInfoFragment = new AllContactsInfoFragment();
            allContactsInfoFragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.allContactsInfoRoot, allContactsInfoFragment).commit();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        allContactsRecycler.getAdapter().notifyDataSetChanged();
    }
}

interface AllContactsInfoFragmentHelper {

    void showContact(Contact contact);
}
