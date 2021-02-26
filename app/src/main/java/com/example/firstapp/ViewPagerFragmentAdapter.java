package com.example.firstapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerFragmentAdapter extends FragmentStateAdapter {

    public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        AllContactsFragment allContactsFragment = new AllContactsFragment();
        allContactsFragment.setArguments(bundle);
        return allContactsFragment;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
