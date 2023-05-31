package com.example.login.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.login.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class RegisterActivity extends AppCompatActivity {
    private EditText editNama, editEmail, editPassword, editKonfirmasiPassword;
    private Button btnLogin, btnRegister;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editNama = findViewById(R.id.nama);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        editKonfirmasiPassword = findViewById(R.id.confirm_password);
        btnLogin = findViewById(R.id.login);
        btnRegister = findViewById(R.id.register);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.setCancelable(false);

        btnLogin.setOnClickListener(view -> {
            finish();
        });
        btnRegister.setOnClickListener(view -> {
            if (editNama.getText().length()>0 && editEmail.getText().length()>0 && editPassword.getText().length()>0 && editKonfirmasiPassword.getText().length()>0){
                if (editPassword.getText().toString().equals(editKonfirmasiPassword.getText().toString())){
                    register(editNama.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString());
                }else{
                    Toast.makeText(getApplicationContext(), "Silahkan masukkan password yang sama !!", Toast.LENGTH_SHORT).show();
                }
            }else{
                Toast.makeText(getApplicationContext(), "Silahkan lengkapi data !!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void register (String nama, String email, String password){
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && task.getResult() !=null){
                    FirebaseUser firebaseUser = task.getResult().getUser();
                    if (firebaseUser !=null) {
                        UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                                .setDisplayName(nama)
                                .build();
                        firebaseUser.updateProfile(request).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                reload();
                            }
                        });
                    }else{
                        Toast.makeText(getApplicationContext(), "Register gagal !", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void reload(){
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}