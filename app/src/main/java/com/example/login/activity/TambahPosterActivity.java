package com.example.login.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.login.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TambahPosterActivity extends AppCompatActivity {
    FirebaseFirestore mStore;
    FirebaseAuth mAuth;
    ImageView ImgBack, ImgHome, edtImage;
    TextView Title;
    EditText edtJudul, edtDeskripsi;
    Button btnSave;
    ProgressDialog progressDialog;
    String idPoster;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_poster);

        ImgBack = findViewById(R.id.ibBack);
        Title = findViewById(R.id.tvTitle);
        ImgHome = findViewById(R.id.ivHomeButton);
        edtImage = findViewById(R.id.edtImage);
        edtJudul = findViewById(R.id.edtJudul);
        edtDeskripsi = findViewById(R.id.edtDeskripsi);
        btnSave = findViewById(R.id.btnSimpan);

        mStore = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        progressDialog = new ProgressDialog(TambahPosterActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setMessage("Saving...");

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), PosterAdmin.class));
        });
        Title.setText("Tambah Poster Saya");
        ImgHome.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        edtImage.setOnClickListener(view -> {
            selectImage();
        });
        btnSave.setOnClickListener(view -> {
            if (edtJudul.getText().length()>0 && edtDeskripsi.getText().length()>0){
                upload(edtJudul.getText().toString(), edtDeskripsi.getText().toString(), mAuth.getCurrentUser().getUid());
            }else{
                Toast.makeText(getApplicationContext(), "Fill in all data!", Toast.LENGTH_SHORT).show();
            }
        });
        Intent intent = getIntent();
        if (intent != null){
            idPoster = intent.getStringExtra("id");
            edtJudul.setText(intent.getStringExtra("Judul"));
            edtDeskripsi.setText(intent.getStringExtra("Deskripsi"));
            Glide.with(getApplicationContext()).load(intent.getStringExtra("Image")).into(edtImage);
        }
    }
    private void selectImage(){
        final CharSequence[] items = {"Take Photo", "Choose from Library", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(TambahPosterActivity.this);
        builder.setTitle(getString(R.string.app_name));
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setItems(items, (dialog, item)->{
           if (items[item].equals("Take Photo")){
               Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
               startActivityForResult(intent, 10);
           }else if (items[item].equals("Choose from Library")){
               Intent intent = new Intent(Intent.ACTION_PICK);
               intent.setType("image/*");
               startActivityForResult(Intent.createChooser(intent, "Select Image"), 20);
           }else if (items[item].equals("Cancel")){
               dialog.dismiss();
           }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 20 && resultCode == RESULT_OK && data != null){
            final Uri path = data.getData();
            Thread thread = new Thread(() -> {
               try {
                   InputStream inputStream = getContentResolver().openInputStream(path);
                   Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                   edtImage.post(()->{
                       edtImage.setImageBitmap(bitmap);
                   });
               }catch (IOException e){
                   e.printStackTrace();
               }
            });
            thread.start();
        }
        if (requestCode == 10 && resultCode == RESULT_OK){
            final Bundle extras = data.getExtras();
            Thread thread = new Thread(() -> {
                Bitmap bitmap = (Bitmap) extras.get("data");
                edtImage.post(() -> {
                   edtImage.setImageBitmap(bitmap);
                });
            });
            thread.start();
        }
    }
    private void upload(String judul, String deskripsi, String userIdUploader){
        edtImage.setDrawingCacheEnabled(true);
        edtImage.buildDrawingCache();
        Bitmap bitmap = ((BitmapDrawable) edtImage.getDrawable()).getBitmap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference reference = storage.getReference("ImagePoster").child(new Date().getTime() + ".jpeg");
        UploadTask uploadTask = reference.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                if (taskSnapshot.getMetadata() != null){
                    if (taskSnapshot.getMetadata().getReference() != null){
                        taskSnapshot.getMetadata().getReference().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.getResult() != null){
                                    saveData(judul, deskripsi, userIdUploader, task.getResult().toString());
                                }else{
                                    progressDialog.dismiss();
                                    Toast.makeText(getApplicationContext(), "Failed !", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }else{
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Failed !", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Failed !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void saveData(String judul, String deskripsi, String userIdUploader, String image) {
        Map<String, Object> user = new HashMap<>();
        user.put("UserId", userIdUploader);
        user.put("Judul", judul);
        user.put("Deskripsi", deskripsi);
        user.put("Image", image);

        progressDialog.show();
        if (idPoster != null) {
            mStore.collection("poster").document(idPoster)
                    .set(user)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Success !", Toast.LENGTH_SHORT).show();
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Failed !", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            mStore.collection("poster")
                    .add(user)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                            finish();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    });
        }
    }
}