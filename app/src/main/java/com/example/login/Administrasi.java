package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class Administrasi extends AppCompatActivity {
    private ImageView ImgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.administrasi);
        ImgBack = findViewById(R.id.back);

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Home.class));
        });
    }
}