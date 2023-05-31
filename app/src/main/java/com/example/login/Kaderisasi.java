package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Kaderisasi extends AppCompatActivity {
    private ImageView ImgBack;
    private Button BtnView1, BtnView2, BtnView3, BtnView4, BtnView5, BtnView6, BtnView7, BtnView8;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kaderisasi);
        ImgBack = findViewById(R.id.back);
        BtnView1 = findViewById(R.id.btn_aswaja);
        BtnView2 = findViewById(R.id.btn_nu);
        BtnView3 = findViewById(R.id.btn_ipnuippnu);
        BtnView4 = findViewById(R.id.btn_kebangsaan);
        BtnView5 = findViewById(R.id.btn_managemen);
        BtnView6 = findViewById(R.id.btn_komunikasi);
        BtnView7 = findViewById(R.id.btn_problem);
        BtnView8 = findViewById(R.id.btn_diskusi);


        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Home.class));
        });

        BtnView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(Kaderisasi.this, PdfView.class);
                startActivity(view);
            }
        });

        BtnView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(Kaderisasi.this, PdfView.class);
                startActivity(view);
            }
        });
    }
}