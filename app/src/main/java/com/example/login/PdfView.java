package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.joanzapata.pdfview.PDFView;

public class PdfView extends AppCompatActivity {
    private PDFView pdfView1, pdfView2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pdf_view);
        pdfView1 = findViewById(R.id.pdf_view1);
        pdfView1.fromAsset("skripsi.pdf")
                .swipeVertical(true)
                .load();
    }
}