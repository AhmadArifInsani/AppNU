package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.example.login.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfilActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private ImageView ImgBack;
    private Button btnLogout, btnEdit;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        ImgBack = findViewById(R.id.ibBack);
        btnLogout = findViewById(R.id.logout);
        btnEdit = findViewById(R.id.btnEdit);

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });
        btnLogout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });
        btnEdit.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), EditProfilActivity.class));
        });
    }
}