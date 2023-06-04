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
    private ImageView ImgBack, ImgHome;
//    private Button BtnView1;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaderisasi);
        ImgBack = findViewById(R.id.ibBack);
//        BtnView1 = findViewById(R.id.btn_aswaja);
        ImgHome = findViewById(R.id.ivHomeButton);

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

//        BtnView1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent view = new Intent(KaderisasiActivity.this, PdfView.class);
//                startActivity(view);
//            }
//       });
        ImgHome.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });
    }
}