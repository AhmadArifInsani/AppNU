package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.login.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    private ImageView viewProfil, imgOrganisasi, imgKaderisasi, imgAdministrasi, imgSosialMedia, imgDataAnggota, imgAlumni, imgHome ;
    ImageSlider imageSlider;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        viewProfil = findViewById(R.id.imageView1);
        imgOrganisasi = findViewById(R.id.img_organisasi);
        imgKaderisasi = findViewById(R.id.img_kaderisasi);
        imgAdministrasi = findViewById(R.id.img_administrasi);
        imgSosialMedia = findViewById(R.id.img_media_sosial);
        imgDataAnggota = findViewById(R.id.img_data_anggota);
        imgAlumni = findViewById(R.id.img_alumni);
        imgHome = findViewById(R.id.ivHomeButton);

        imageSlider = findViewById(R.id.slide);

        viewProfil.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
        });

        ArrayList<SlideModel> imageList = new ArrayList<>();

        imageList.add(new SlideModel(R.drawable.image_organisasi_1,null));
        imageList.add(new SlideModel(R.drawable.image_organisasi_2,null));
        imageList.add(new SlideModel(R.drawable.image_sosialmedia_1,null));
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);

        imgOrganisasi.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), BeritaActivity.class));
        });

        imgKaderisasi.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), KaderisasiActivity.class));
        });

        imgAdministrasi.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AdministrasiActivity.class));
        });

        imgSosialMedia.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), SosialMediaActivity.class));
        });

        imgDataAnggota.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), DataAnggotaActivity.class));
        });

        imgAlumni.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), AlumniActivity.class));
        });

        imgHome.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });
    }
}