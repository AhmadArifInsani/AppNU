package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.login.R;

public class EditProfilActivity extends AppCompatActivity {
    private ImageView ImgBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        ImgBack = findViewById(R.id.ibBack);

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
        });
    }
}