package com.example.login.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.adapter.AdapterBerita;
import com.example.login.adapter.AdapterBeritaAdmin;
import com.example.login.adapter.AdapterSosialMedia;
import com.example.login.model.BeritaModelAdmin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BeritaKegiatan extends AppCompatActivity {
    FirebaseFirestore mStore;
    ImageView ImgBack, ImgHome;
    TextView Title;
    RecyclerView recyclerView;
    List<BeritaModelAdmin> models = new ArrayList<>();
    AdapterBerita adapterBerita;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_kegiatan);
        ImgBack = findViewById(R.id.ibBack);
        ImgHome = findViewById(R.id.ivHomeButton);
        recyclerView =findViewById(R.id.rvBerita);
        Title = findViewById(R.id.tvTitle);

        mStore = FirebaseFirestore.getInstance();

        Title.setText("Berita Kegiatan");
        progressDialog = new ProgressDialog(BeritaKegiatan.this);
        progressDialog.setTitle("Loading..");
        progressDialog.setMessage("Retrieve data..");

        ImgBack.setOnClickListener(view -> {
            onBackPressed();
        });
        ImgHome.setOnClickListener(view -> {
            startActivity(new Intent(this, HomeActivity.class));
        });

        getData();
        adapterBerita = new AdapterBerita(this, models);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterBerita);
    }
    private void getData(){
        progressDialog.show();
        mStore.collection("berita")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        models.clear();
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                Log.d("uwu", document.toString());
                                BeritaModelAdmin beritaModelAdmin = document.toObject(BeritaModelAdmin.class);
                                //    PosterModel posterModel = document.toObject(PosterModel.class);
                                //    posterModel.setId(document.getId());
                                beritaModelAdmin.setId(document.getId());
                                models.add(beritaModelAdmin);
                            }
                            adapterBerita.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getApplicationContext(), "Data failed to fetch!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

}