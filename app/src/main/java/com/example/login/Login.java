package com.example.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    private EditText editEmail, editPassword;
    private Button btnLogin;
    private TextView txtRegister;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        editEmail = findViewById(R.id.email);
        editPassword = findViewById(R.id.password);
        btnLogin = findViewById(R.id.login);
        txtRegister = findViewById(R.id.button_register);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(Login.this);
        progressDialog.setTitle("Loading");
        progressDialog.setCancelable(false);

        txtRegister.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), Register.class));
        });
        btnLogin.setOnClickListener(view -> {
            if (editEmail.getText().length()>0 && editPassword.getText().length()>0){
                login(editEmail.getText().toString(), editPassword.getText().toString());
            }else{
                Toast.makeText(getApplicationContext(), "Silahkan isi semua data !", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void login (String email, String password){
        mAuth.signInWithEmailAndPassword(email, password). addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful() && task.getResult() !=null){
                    if (task.getResult().getUser() !=null){

                    }else{
                        Toast.makeText(getApplicationContext(), "Login Sukses", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Login Gagal", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void reload(){

    }

    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            reload();
        }
    }
}