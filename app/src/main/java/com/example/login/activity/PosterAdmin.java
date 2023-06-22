package com.example.login.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.example.login.adapter.AdapterPosterAdmin;
import com.example.login.model.PosterModelAdmin;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class PosterAdmin extends AppCompatActivity {
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    ImageView ImgBack, ImgAdd;
    TextView Title;
    RecyclerView recyclerView;
    List<PosterModelAdmin> list;
    AdapterPosterAdmin adapter;
    ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poster_admin);

        ImgBack = findViewById(R.id.ibBack);
        recyclerView =findViewById(R.id.recyclerView);
        Title = findViewById(R.id.tvTitle);
        ImgAdd = findViewById(R.id.ivAddButton);

        mStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(PosterAdmin.this);
        progressDialog.setTitle("Loading..");
        progressDialog.setMessage("Retrieve data..");

        list = new ArrayList<>();
        adapter = new AdapterPosterAdmin(getApplicationContext(), list);
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setDialog(new AdapterPosterAdmin.Dialog() {
            @Override
            public void onClick(int pos) {
                final CharSequence[] dialogitem = {"Edit", "Delete"};
                AlertDialog.Builder dialog = new AlertDialog.Builder(PosterAdmin.this);
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i){
                            case 0:
                                Intent intent = new Intent(getApplicationContext(), TambahPoster.class);
                                intent.putExtra("id",  list.get(pos).getId());
                                intent.putExtra("Judul",  list.get(pos).getJudul());
                                intent.putExtra("Deskripsi",  list.get(pos).getDeskripsi());
                                intent.putExtra("Image", list.get(pos).getImage());
                                startActivity(intent);
                                break;
                            case 1:
                                deleteData(list.get(pos).getId());
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });
        Title.setText("Poster Saya");

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
        });
        ImgAdd.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), TambahPoster.class));
        });

    }
    protected void onStart(){
        super.onStart();
        getData();
    }
    private void getData(){
        progressDialog.show();
        mStore.collection("poster")
                .whereEqualTo("UserId", mAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        list.clear();
                        if (task.isSuccessful()){
                            for (QueryDocumentSnapshot document : task.getResult()){
                                PosterModelAdmin posterModelAdmin = document.toObject(PosterModelAdmin.class);
                                posterModelAdmin.setId(document.getId());
                                list.add(posterModelAdmin);
                            }
                            adapter.notifyDataSetChanged();
                        }else{
                            Toast.makeText(getApplicationContext(), "Data failed to fetch!", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    private void deleteData(String id){
        progressDialog.show();
        mStore.collection("poster").document(id)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Data failed to delete", Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                        getData();
                    }
                });
    }
}