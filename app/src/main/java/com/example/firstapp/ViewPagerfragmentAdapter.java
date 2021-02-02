package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerfragmentAdapter extends FragmentStateAdapter {

    public ViewPagerfragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new AllContactsFragment(position);
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
