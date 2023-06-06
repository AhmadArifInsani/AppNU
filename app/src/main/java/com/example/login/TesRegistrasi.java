package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class TesRegistrasi extends AppCompatActivity {
    EditText NmLengkap, Email, Password, ConfrmPass;
    Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tes_registrasi);
        NmLengkap = findViewById(R.id.nama);
        Email = findViewById(R.id.email);
        Password = findViewById(R.id.password);
        ConfrmPass = findViewById(R.id.confirm_password);
        btnRegister = findViewById(R.id.register);
        btnLogin = findViewById(R.id.login);
    }
}