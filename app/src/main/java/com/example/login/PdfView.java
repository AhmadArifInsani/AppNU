package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.login.activity.KaderisasiActivity;
import com.example.login.model.KaderisasiModel;


public class PdfView extends AppCompatActivity {
    private WebView webView;
    private ImageView ImgBack;
    private KaderisasiModel kaderisasiModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_pdf_view);
        ImgBack = findViewById(R.id.ibBack);

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), KaderisasiActivity.class));
        });

        setWebView();

    }

    private void setWebView() {
        webView = findViewById(R.id.webView);
        kaderisasiModel = getIntent().getParcelableExtra("kaderisasiModel");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSupportZoom(true);

        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(kaderisasiModel.getUrlFile());

    }

    private class MyWebViewClient extends WebViewClient {

    }
}