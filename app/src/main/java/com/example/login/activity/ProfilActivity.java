package com.example.login.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilActivity extends AppCompatActivity {
    private FirebaseUser firebaseUser;
    StorageReference storageReference;
    FirebaseFirestore mStore;
    private FirebaseAuth mAuth;
    private ImageView ImgBack;
    private Button btnLogout, btnEdit;
    CircleImageView ftoProfil;
    private TextView TxtName, TxtNomorTlp, TxtEmail, TxtPimpinan, TxtBirthday, Title;
    String userId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        ImgBack = findViewById(R.id.ibBack);
        btnLogout = findViewById(R.id.logout);
        btnEdit = findViewById(R.id.btnEdit);
        TxtName = findViewById(R.id.nama);
        TxtNomorTlp = findViewById(R.id.nomor);
        TxtEmail = findViewById(R.id.text_email);
        TxtPimpinan = findViewById(R.id.text_pimpinan);
        TxtBirthday = findViewById(R.id.text_birthday);
        Title = findViewById(R.id.tvTitle);
        ftoProfil = findViewById(R.id.profil);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        if (mAuth.getCurrentUser() != null){
            userId = mAuth.getCurrentUser().getUid();
        }

        Title.setText("Profil");

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        StorageReference profileRef = storageReference.child("users/"+mAuth.getCurrentUser().getUid()+"profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(ftoProfil);
            }
        });

        DocumentReference documentReference = mStore.collection("users").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if (documentSnapshot.exists()){
                    TxtName.setText(documentSnapshot.getString("name"));
                    TxtNomorTlp.setText(documentSnapshot.getString("phone"));
                    TxtEmail.setText(documentSnapshot.getString("email"));
                    TxtPimpinan.setText(documentSnapshot.getString("pimpinan"));
                    TxtBirthday.setText(documentSnapshot.getString("birthday"));
                }else{
                    Log.d("tag", "onEvent : Document do not exists");
                }
            }
        });

        btnEdit.setOnClickListener(view -> {
            Intent i = new Intent(view.getContext(), EditProfilActivity.class);
            i.putExtra("name", TxtName.getText().toString());
            i.putExtra("email", TxtEmail.getText().toString());
            i.putExtra("pimpinan", TxtPimpinan.getText().toString());
            i.putExtra("birthday", TxtBirthday.getText().toString());
            i.putExtra("phone", TxtNomorTlp.getText().toString());
            startActivity(i);
        });

        btnLogout.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        });

    }
}