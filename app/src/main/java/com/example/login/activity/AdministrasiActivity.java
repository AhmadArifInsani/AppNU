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
      //  dataFromFirebase();
        models.add(new AdministrasiModel("Pedoman Administrasi", "gs://appnu-a152e.appspot.com/FileSurat/Pemberitahuan.docx"));
        models.add(new AdministrasiModel("Surat Rekom", "gs://appnu-a152e.appspot.com/FileSurat/Pemberitahuan.docx"));
        models.add(new AdministrasiModel("Pengajuan SK", "gs://appnu-a152e.appspot.com/FileSurat/Pemberitahuan.docx"));
        models.add(new AdministrasiModel("Surat", "gs://appnu-a152e.appspot.com/FileSurat/Pemberitahuan.docx"));
    }
   /* private void dataFromFirebase(){
        if (models.size() > 0)
            models.clear();
        mStore.collection("administrasi")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot documentSnapshot : task.getResult()){
                            AdministrasiModel administrasiModel = new AdministrasiModel(documentSnapshot.getString("nama"), documentSnapshot.getString("link"));
                            models.add(administrasiModel);
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AdministrasiActivity.this, "Error..", Toast.LENGTH_SHORT).show();
                    }
                });
    }*/
    private void setUpRecyclerView(){
        adapterAdministrasi = new AdapterAdministrasi(this, models);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapterAdministrasi);
    }

}