package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.login.R;
import com.example.login.adapter.AdapterKaderisasi;
import com.example.login.model.KaderisasiModel;

import java.util.ArrayList;
import java.util.List;

public class KaderisasiActivity extends AppCompatActivity {
    private ImageView ImgBack, ImgHome;
    private RecyclerView rvKaderisasi;
    TextView Title;
    ArrayList<KaderisasiModel> listKaderisasi;
    private AdapterKaderisasi adapterKaderisasi;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaderisasi);

        ImgBack = findViewById(R.id.ibBack);
        ImgHome = findViewById(R.id.ivHomeButton);
        rvKaderisasi = findViewById(R.id.recyclerMateri);
        Title = findViewById(R.id.tvTitle);
        listKaderisasi = new ArrayList<>();

        Title.setText("Materi-Materi");

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        ImgHome.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });
        listKaderisasi.add(new KaderisasiModel("Materi Aswaja", "https://drive.google.com/file/d/18aGzAwoT66MLsTDzYHmybSADdnC063OH/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("Materi NU", "https://drive.google.com/file/d/1ImIEXtKVWdf7FiFPuNUTvbG0or6CEla1/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("Materi IPNU IPPNU", "https://drive.google.com/file/d/1LkH8SOeMPxf7YDqqPcUFS9AOnRDnTzvL/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("Materi Kpemimpinan", "https://drive.google.com/file/d/1FI5JpAmVmDE3NeAN9lkO-lob_9Zw1362/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("Materi Organisasi", "https://drive.google.com/file/d/1BcYY_ca0-ydBIBxEIikdFINtUG3dVIm4/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("Hasil Kongres PP IPNU", "https://drive.google.com/file/d/1he2LFmT1RTAyv9ruY1J-En3Zdm10ZIb3/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("Hasil Kongres PP IPPNU", "https://drive.google.com/file/d/1JL73giysjqoaXBt1Naje6RDpAc4YhPol/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("PD PRT PP IPNU", "https://drive.google.com/file/d/1ooyJ4BXI4PSPaLAhEEsLgwhoW13N8Gm3/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("PD PRT PP IPPNU", "https://drive.google.com/file/d/1_jQlR8Y-cAzycIo6dku4-A3TjUdiIevU/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("Pedoman Kaderisasi PP IPNU", "https://drive.google.com/file/d/15wOOcLQ6x8bQWGEE2OPQyKIaRn1KTBVP/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("Pedoman Kaderisasi PP IPPNU", "https://drive.google.com/file/d/10QPSDtK95TqRwtH-X4gUgprQiXPqtI9x/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("Juklak L-KPP PP IPPNU", "https://drive.google.com/file/d/18N7a7ltorFQ49qT52cA4jrP7jh8KGNo2/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("PPOA PP IPNU", "https://drive.google.com/file/d/14RZsvli3FrKD5eX_23t9Kz_uQTJt6i1C/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("PPOA PP IPPNU", "https://drive.google.com/file/d/1V5u_7761t83QkaLQnXPU2OVVasQzFHa4/view?usp=sharing"));
        listKaderisasi.add(new KaderisasiModel("PPOK PP IPPNU", "https://drive.google.com/file/d/1EHTf0Gk14WxoJrTfF9EaNwdH90WKW7bz/view?usp=sharing"));

        setRecyclerView();
    }

    private void setRecyclerView() {
        adapterKaderisasi = new AdapterKaderisasi(this, listKaderisasi);
        rvKaderisasi.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        rvKaderisasi.setAdapter(adapterKaderisasi);
    }

}