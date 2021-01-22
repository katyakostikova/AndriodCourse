package com.example.firstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);

        Intent intent =  getIntent();
        TextView nameTextView = findViewById(R.id.nameFound);
        TextView numberTextView = findViewById(R.id.numberFound);
        nameTextView.setText(intent.getExtras().getString("contactName"));
        numberTextView.setText(intent.getExtras().getString("contactNumber"));

        Button backButton = findViewById(R.id.buttonBack2);
        View.OnClickListener onBackButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        };
        backButton.setOnClickListener(onBackButtonClickListener);
    }
}