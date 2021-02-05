package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager2 viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            switch (position) {
                case 0:
                    tab.setText("All contacts");
                    break;
                case 1:
                    tab.setText("Recent");
                    break;
                case 2:
                    tab.setText("Favourites");
                    break;
            }
        });

        viewPager.setAdapter( new ViewPagerFragmentAdapter(this));
        tabLayoutMediator.attach();

        FloatingActionButton addButton = findViewById(R.id.floatingActionButtonAdd);

        View.OnClickListener onAddButtonClickListener = v -> {
            Intent intent = new Intent(MainActivity.this, AddContactActivity.class);
            MainActivity.this.startActivity(intent);
        };

        ContactManager.getInstance().addOnContactsWasChangedListener(new ContactManager.OnContactsWasChanged() {
            @Override
            public void dataWasChanged() {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        addButton.setOnClickListener(onAddButtonClickListener);
    }

}

