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
        ViewAllContactsRecyclerViewAdapter viewAllContactsRecyclerViewAdapter = new ViewAllContactsRecyclerViewAdapter(ContactManager.getInstance().getAll(), this, openInNewActivity);
        if (tabPosition == 1) {
            try {
                int fromIndex = ContactManager.getInstance().size() - 3;
                int toIndex = ContactManager.getInstance().size();
                viewAllContactsRecyclerViewAdapter = new ViewAllContactsRecyclerViewAdapter( ContactManager.getInstance().getAll().subList(fromIndex, toIndex), this, openInNewActivity);
            } catch (Exception err) {
                viewAllContactsRecyclerViewAdapter = new ViewAllContactsRecyclerViewAdapter(ContactManager.getInstance().getContactFavourites(), this, openInNewActivity);
            }
        } else if (tabPosition == 2) {
            viewAllContactsRecyclerViewAdapter = new ViewAllContactsRecyclerViewAdapter(ContactManager.getInstance().getContactFavourites(), this, openInNewActivity);
        }
        allContactsRecycler.setAdapter(viewAllContactsRecyclerViewAdapter);
        allContactsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void showContact(int position) {
        Contact contact = ContactManager.getInstance().getById(position);
        if (getActivity().findViewById(R.id.allContactsInfoRoot) == null) {
            Intent intent = new Intent(getActivity(), InfoContactActivity.class);
            intent.putExtra("contactName", contact.getContactName());
            intent.putExtra("contactNumber", contact.getPhoneNumber());
            startActivity(intent);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("contactName", contact.getContactName());
            bundle.putString("contactNumber", contact.getPhoneNumber());

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

    void showContact(int position);
}
