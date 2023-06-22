package com.example.login.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.adapter.AdapterBeritaAdmin;
import com.example.login.model.BeritaModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BeritaAdmin extends AppCompatActivity {
    FirebaseFirestore mStore;
    FirebaseAuth mAuth;
    ImageView ImgBack, ImgAdd;
    TextView Title;
    RecyclerView recyclerView;
    List<BeritaModel> models;
    AdapterBeritaAdmin adapter;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berita_admin);
        ImgBack = findViewById(R.id.ibBack);
        recyclerView = findViewById(R.id.rvBerita);
        Title = findViewById(R.id.tvTitle);
        ImgAdd = findViewById(R.id.ivAddButton);

        mStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(BeritaAdmin.this);
        progressDialog.setTitle("Loading..");
        progressDialog.setMessage("Retrieve data..");

        models = new ArrayList<>();
        adapter = new AdapterBeritaAdmin(getApplicationContext(), models);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setDialog(new AdapterBeritaAdmin.Dialog() {
            @Override
            public void onClick(int pos) {
                final CharSequence[] dialogitem = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(BeritaAdmin.this);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), TambahBerita.class);
                                intent.putExtra("id", models.get(pos).getId());
                                intent.putExtra("Judul", models.get(pos).getJudul());
                                intent.putExtra("Deskripsi", models.get(pos).getDeskripsi());
                                intent.putExtra("Image", models.get(pos).getImage());
                                startActivity(intent);
                                break;
                            case 1:
                                deleteData(models.get(pos).getId());
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        Title.setText("Berita Saya");

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
        });
        ImgAdd.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), TambahBerita.class));
        });
    }

    protected void onStart() {
        super.onStart();
        getData();
    }

    private void getData() {
        progressDialog.show();
        mStore.collection("berita")
                .whereEqualTo("UserId", mAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        models.clear();
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                BeritaModel beritaModel = document.toObject(BeritaModel.class);
                                //    PosterModel posterModel = document.toObject(PosterModel.class);
                                //    posterModel.setId(document.getId());
                                beritaModel.setId(document.getId());
                                models.add(beritaModel);
                            }
                            adapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(getApplicationContext(), "Data failed to fetch!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void deleteData(String id) {
        progressDialog.show();
        mStore.collection("berita").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Data failed to delete", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }
}