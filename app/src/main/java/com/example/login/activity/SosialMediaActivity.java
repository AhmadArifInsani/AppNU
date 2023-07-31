package com.example.login.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.adapter.AdapterPoster;
import com.example.login.adapter.AdapterPosterAdmin;
import com.example.login.model.PosterModelAdmin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SosialMediaActivity extends AppCompatActivity {
    FirebaseFirestore mStore;
    ImageView ImgBack, ImgHome;
    TextView Title;
    RecyclerView recyclerView;
    List<PosterModelAdmin> list;
    AdapterPoster adapter;
    ProgressDialog progressDialog;
    //List<String> text;
    // List<Integer> image;
    // AdapterSosialMedia adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sosial_media);

        ImgBack = findViewById(R.id.ibBack);
        ImgHome = findViewById(R.id.ivHomeButton);
        recyclerView = findViewById(R.id.recyclerView);
        Title = findViewById(R.id.tvTitle);

        mStore = FirebaseFirestore.getInstance();

        //text = new ArrayList<>();
        // image = new ArrayList<>();

        Title.setText("Poster Event");

        progressDialog = new ProgressDialog(SosialMediaActivity.this);
        progressDialog.setTitle("Loading..");
        progressDialog.setMessage("Retrieve data..");

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });
        ImgHome.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        list = new ArrayList<>();
        adapter = new AdapterPoster(this, list);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    protected void onStart() {
        super.onStart();
        getData();
    }

    private void getData() {
        progressDialog.show();
        mStore.collection("poster")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                PosterModelAdmin posterModelAdmin = document.toObject(PosterModelAdmin.class);
                                //PosterModelAdmin posterModelAdmin = new PosterModelAdmin(document.getString("Judul"), document.getString("Deskripsi"), document.getString("Image"));
                                //    PosterModel posterModel = document.toObject(PosterModel.class);
                                //    posterModel.setId(document.getId());
                                posterModelAdmin.setId(document.getId());
                                list.add(posterModelAdmin);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(), "Data failed to fetch!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
}