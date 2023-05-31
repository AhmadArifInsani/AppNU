package com.example.login;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profil extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private ImageView ImgBack;
    private Button btnLogout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profil);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ImgBack = findViewById(R.id.back);
        btnLogout = findViewById(R.id.logout);

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Home.class));
        });
        btnLogout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), Login.class));
            finish();
        });
    }
}