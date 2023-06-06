package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.login.R;
import com.example.login.model.KaderisasiModel;

import java.util.ArrayList;
import java.util.List;

public class KaderisasiActivity extends AppCompatActivity {
    private ImageView ImgBack, ImgHome;
//    private Button BtnView1;
    private RecyclerView pdfList;

    List<KaderisasiModel> listKaderisasi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaderisasi);
        ImgBack = findViewById(R.id.ibBack);
//        BtnView1 = findViewById(R.id.btn_aswaja);
        ImgHome = findViewById(R.id.ivHomeButton);
        listKaderisasi = new ArrayList<>();

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
        listKaderisasi.add(new KaderisasiModel("Materi Aswaja", "https://github.com/AhmadArifInsani/appnu/blob/master/app/src/main/assets/aswaja.pdf"));

    }
}