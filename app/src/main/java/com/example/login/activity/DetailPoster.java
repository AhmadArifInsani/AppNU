package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.login.R;
import com.example.login.model.PosterModelAdmin;

public class DetailPoster extends AppCompatActivity {
    ImageView ImgBack, ImgHome, ImgProfil, Image;
    TextView Title, User, Pimpinan, Deskripsi, Date;
    PosterModelAdmin posterModelAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_poster);

        ImgBack = findViewById(R.id.ibBack);
        ImgHome = findViewById(R.id.ivHomeButton);
        Title = findViewById(R.id.tvTitle);

        Title.setText("Poster Event");

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SosialMediaActivity.class));
        });

        ImgHome.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });
        getData();
    }
    public void getData(){
        Image = findViewById(R.id.imageDetailPoster);
        Deskripsi = findViewById(R.id.detailPoster);

        posterModelAdmin = getIntent().getParcelableExtra("PosterModel");
        Glide.with(this).load(posterModelAdmin.getImage()).into(Image);
        Deskripsi.setText(posterModelAdmin.getDeskripsi());

    }
}