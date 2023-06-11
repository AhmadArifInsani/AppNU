package com.example.login.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import io.grpc.Context;

public class EditProfilActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    StorageReference storageReference;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    FirebaseUser user;
    private ImageView ImgBack;
    TextView ubhFoto;
    CircleImageView ftoProfil;
    EditText EtNama, EtEmail, EtPimpinan, EtBirthday, EtNomorTlp;
    Button btnSave;
    String userId;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        EtNama = findViewById(R.id.etNama);
        EtEmail = findViewById(R.id.etEmail);
        EtPimpinan = findViewById(R.id.etPimpinan);
        EtBirthday = findViewById(R.id.etBirthday);
        EtNomorTlp = findViewById(R.id.etNomorTlp);
        ImgBack = findViewById(R.id.ibBack);
        ubhFoto = findViewById(R.id.text_ubah_foto);
        ftoProfil = findViewById(R.id.profil);
        btnSave = findViewById(R.id.btn_simpan);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();

        userId = mAuth.getCurrentUser().getUid();
        storageReference = FirebaseStorage.getInstance().getReference();
        DocumentReference documentReference = mStore.collection("users").document(userId);

        StorageReference profileRef = storageReference.child("users/"+mAuth.getCurrentUser().getUid()+"profile.jpg");
        profileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(ftoProfil);
            }
        });

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
        });

        ubhFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openGalleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(openGalleryIntent, 1000);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (EtNama.getText().toString().isEmpty() || EtEmail.getText().toString().isEmpty() || EtPimpinan.getText().toString().isEmpty() || EtBirthday.getText().toString().isEmpty() || EtNomorTlp.getText().toString().isEmpty()){
                    Toast.makeText(EditProfilActivity.this, "One or many fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                String email = EtEmail.getText().toString();
                user.updateEmail(email).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        DocumentReference docRef = mStore.collection("users").document(user.getUid());
                        Map<String, Object> edited = new HashMap<>();
                        edited.put("email", email);
                        edited.put("name", EtNama.getText().toString());
                        edited.put("pimpinan", EtPimpinan.getText().toString());
                        edited.put("birthday", EtBirthday.getText().toString());
                        edited.put("phone", EtNomorTlp.getText().toString());
                        docRef.update(edited).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(EditProfilActivity.this, "Profile update", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), ProfilActivity.class));
                                finish();
                            }
                        });
                        Toast.makeText(EditProfilActivity.this, "Profile is changed", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditProfilActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        Intent data = getIntent();
        String nama = data.getStringExtra("name");
        String email = data.getStringExtra("email");
        String pimpinan = data.getStringExtra("pimpinan");
        String birthday = data.getStringExtra("birthday");
        String nomorTlp = data.getStringExtra("phone");

        EtNama.setText(nama);
        EtEmail.setText(email);
        EtPimpinan.setText(pimpinan);
        EtBirthday.setText(birthday);
        EtNomorTlp.setText(nomorTlp);


        Log.d(TAG, "onCreate:" + nama + " " + email + " " + pimpinan + " " + birthday + " " + nomorTlp);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            if (resultCode == Activity.RESULT_OK){
                Uri imageUri = data.getData();
                // ftoProfil.setImageURI(imageUri);
                uploadImageToFirebase(imageUri);
            }
        }
    }

    private void uploadImageToFirebase(Uri imageUri) {
        StorageReference fileRef = storageReference.child("users/"+mAuth.getCurrentUser().getUid()+"profile.jpg");
        fileRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Picasso.get().load(uri).into(ftoProfil);
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(EditProfilActivity.this, "Failed",Toast.LENGTH_SHORT).show();
            }
        });
    }
}