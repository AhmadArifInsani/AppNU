package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Home extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private ImageView viewProfil, ImgOrganisasi, ImgKaderisasi, ImgAdministrasi, ImgSosialMedia, ImgDataAnggota, ImgAlumni, ImgHome;
    ImageSlider imageSlider;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        viewProfil = findViewById(R.id.imageView1);
        ImgOrganisasi = findViewById(R.id.img_organisasi);
        ImgKaderisasi = findViewById(R.id.img_kaderisasi);
        ImgAdministrasi = findViewById(R.id.img_administrasi);
        ImgSosialMedia = findViewById(R.id.img_media_sosial);
        ImgDataAnggota = findViewById(R.id.img_data_anggota);
        ImgAlumni = findViewById(R.id.img_alumni);
        imageSlider = findViewById(R.id.slide);

        viewProfil.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Profil.class));
        });
        ImgOrganisasi.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Organisasi.class));
        });
        ImgKaderisasi.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Kaderisasi.class));
        });
        ImgAdministrasi.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Administrasi.class));
        });
        ImgSosialMedia.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SosialMedia.class));
        });
        ImgDataAnggota.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), DataAnggota.class));
        });
        ImgAlumni.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Alumni.class));
        });

        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.image_organisasi_1,null));
        imageList.add(new SlideModel(R.drawable.image_organisasi_2,null));
        imageList.add(new SlideModel(R.drawable.image_sosialmedia_1,null));
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);
    }
}