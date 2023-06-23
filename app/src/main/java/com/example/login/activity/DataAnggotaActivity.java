package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.adapter.AdapterDataAnggota;
import com.example.login.adapter.AdapterKaderisasi;
import com.example.login.model.DataAnggotaModel;

import java.util.ArrayList;
import java.util.List;

public class DataAnggotaActivity extends AppCompatActivity {
    private ImageView ImgBack, ImgHome;
    TextView Title;
    RecyclerView recyclerView;
    ArrayList<DataAnggotaModel> models;
    AdapterDataAnggota adapterDataAnggota;
    SearchView searchView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_anggota);
        ImgBack = findViewById(R.id.ibBack);
        ImgHome = findViewById(R.id.ivHomeButton);
        Title = findViewById(R.id.tvTitle);
        recyclerView = findViewById(R.id.rvDataAnggota);
        searchView = findViewById(R.id.search);

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });
        ImgHome.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        Title.setText("Data Anggota");
        models = new ArrayList<>();
        searchView.clearFocus();

        //Data Anggota
        models.add(new DataAnggotaModel(R.drawable.person, "Bayu Satriyo", "07 Oktober 1999", "bayusatriyo@gmail.com", "PR Plosokerep", "+62-853-3622-5993", "https://wa.me/6285336225993?text=Hi,Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Arti Wahyu Ningrum", "10 Juni 2003", "arum10@gmail.com", "PR Plosokerep", "+62-815-5976-5140", "https://wa.me/6281559765140?text=Hi,Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Fiky Firmansyah", "06 Desember 2002", "fikyfirmansyah12@gmail.com", "PR Plosokerep", "+62-877-5577-8954", "https://wa.me/6287755778954?text=Hi,Is any one Available?"));
        setRecyclerView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterList(newText);
                return true;
            }
        });
    }
    private void setRecyclerView() {
        adapterDataAnggota = new AdapterDataAnggota(this, models);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapterDataAnggota);
    }
    private void filterList(String text){
        ArrayList<DataAnggotaModel> filteredList = new ArrayList<>();
        for (DataAnggotaModel item : models){
            if (item.getNama().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapterDataAnggota.setFilteredList(filteredList);
        }
    }

}