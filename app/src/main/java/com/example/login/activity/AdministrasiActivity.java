package com.example.login.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.adapter.AdapterAdministrasi;
import com.example.login.adapter.AdapterKaderisasi;
import com.example.login.model.AdministrasiModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class AdministrasiActivity extends AppCompatActivity {
    FirebaseFirestore mStore;
    private ImageView ImgBack, ImgHome;
    TextView Title;
    RecyclerView recyclerView;
    AdapterAdministrasi adapterAdministrasi;
    ArrayList<AdministrasiModel> models = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrasi);

        ImgBack = findViewById(R.id.ibBack);
        ImgHome = findViewById(R.id.ivHomeButton);
        recyclerView = findViewById(R.id.rvSurat);
        Title = findViewById(R.id.tvTitle);

        Title.setText("Surat Administrasi");

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        ImgHome.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });
        mStore = FirebaseFirestore.getInstance();

        setUpRecyclerView();
        models.add(new AdministrasiModel("Surat Pemberitahuan", "https://docs.google.com/document/d/1IjB9yd9Cmjavzm_lRnM0_o8gvK8rotsj/edit?usp=drive_link&ouid=105971814521327400252&rtpof=true&sd=true"));
        models.add(new AdministrasiModel("Surat Perizinan Tempat", "https://docs.google.com/document/d/1C8-Gdr4d4rbosWzLR-fXF6epxbRS92iM/edit?usp=drive_link&ouid=105971814521327400252&rtpof=true&sd=true"));
        models.add(new AdministrasiModel("Surat Rekomendasi", "https://docs.google.com/document/d/1C4J8HTPFumaQ1_PGL0fbNhgEeTXPmvh8/edit?usp=drive_link&ouid=105971814521327400252&rtpof=true&sd=true"));
        models.add(new AdministrasiModel("Surat Tugas IPNU", "https://docs.google.com/document/d/1HtKt5PVt4BnTUTmUXNS8fiChp9pVF_XG/edit?usp=drive_link&ouid=105971814521327400252&rtpof=true&sd=true"));
        models.add(new AdministrasiModel("Surat Tugas IPPNU", "https://docs.google.com/document/d/1QZNodlIeSfWIUKKt6-dYVblcXFRNG-pL/edit?usp=drive_link&ouid=105971814521327400252&rtpof=true&sd=true"));
        models.add(new AdministrasiModel("Surat Undangan", "https://docs.google.com/document/d/1hoIClhvJacvDfzw8cwyweKX4LejpPsQ4/edit?usp=drive_link&ouid=105971814521327400252&rtpof=true&sd=true"));
    }

    private void setUpRecyclerView() {
        adapterAdministrasi = new AdapterAdministrasi(this, models);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapterAdministrasi);
    }

}