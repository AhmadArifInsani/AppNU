package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.login.PdfView;
import com.example.login.R;

public class KaderisasiActivity extends AppCompatActivity {
    private ImageView ImgBack;
    private Button BtnView1, BtnView2, BtnView3, BtnView4, BtnView5, BtnView6, BtnView7, BtnView8;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaderisasi);
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
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        BtnView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent view = new Intent(KaderisasiActivity.this, PdfView.class);
                startActivity(view);
            }
        });
    }
}