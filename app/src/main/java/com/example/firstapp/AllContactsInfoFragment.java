package com.example.firstapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

public class AllContactsInfoFragment extends Fragment {
    private String contactName;
    private String contactNumber;

    public AllContactsInfoFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        contactName = getArguments().getString("contactName");
        contactNumber = getArguments().getString("contactNumber");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_information_fragment, container, false);
        TextView contactNameTextView =  view.findViewById(R.id.nameFound);
        contactNameTextView.setText(contactName);
        TextView contactNumberTextView =  view.findViewById(R.id.numberFound);
        contactNumberTextView.setText(contactNumber);
        return view;
    }
}
