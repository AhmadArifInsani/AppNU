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
        models.add(new DataAnggotaModel(R.drawable.person, "Achmad Rofiuddin Al A.", "01 Mei 2000", "rofiuddin@gmail.com", "PAC Sumobito", "+62-881-3561-616", "https://wa.me/628813561616?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Ahmad Arif Insani", "26 September 1997", "arifinsani40@gmail.com", "PAC Jombang", "+62-857-0674-7612", "https://wa.me/6285706747612?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Ananda Putra Pratama", "13 September 2001", "putrapratama13@gmail.com", "PAC Gudo", "+62-897-0546-222", "https://wa.me/628970546222?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Arti Wahyu Ningrum", "10 Juni 2003", "arum10@gmail.com", "PAC Perak", "+62-815-5976-5140", "https://wa.me/6281559765140?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Bayu Satriyo", "07 Oktober 1999", "bayusatriyo@gmail.com", "PAC Ngoro", "+62-853-3622-5993", "https://wa.me/6285336225993?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Fani Milatul Mauillah", "27 Januari 2005", "milatul27@gmail.com", "PAC Mojowarno", "+62-858-5260-3104", "https://wa.me/6285852603104?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Fiky Firmansyah", "06 Desember 2002", "fikyfirmansyah12@gmail.com", "PAC Mojoagung", "+62-877-5577-8954", "https://wa.me/6287755778954?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "M. Zainudin", "29 Juli 2001", "jainudin@gmail.com", "PAC Wonosalam", "+62-838-5139-9174", "https://wa.me/6283851399174?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Maslahah", "29 September 2005", "maslahah09@gmail.com", "PAC Bareng", "+62-857-5526-0290", "https://wa.me/6285755260290?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Melzia Vilda Alyani", "06 Juni 2005", "imelvilda@gmail.com", "PAC Bandarkedungmulyo", "+62-878-5579-8850", "https://wa.me/6287855798850?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Nadea Marchella Armei", " 04 Juli 2004", "marchella04@gmail.com", "PAC Peterongan", "+62-888-9716-055", "https://wa.me/628889716055?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Nisfhatur Rohmah", "13 November 2000", "nisfatur@gmail.com", "PAC Tembelang", "+62-819-3476-2594", "https://wa.me/6281934762594?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Nur Avita Firdhaus", "08 Mei 2000", "avita08@gmail.com", "PAC Ploso", "+62-858-5238-5875", "https://wa.me/6285852385875?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Radit Ichwanul Firdaus", "26 Februari 2005", "raditsamat@gmail.com", "PAC Diwek", "+62-857-4867-8060", "https://wa.me/6285748678060?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Robby Alfansyah Ariyanto", "19 Mei 2003", "robyyalfa@gmail.com", "PAC Jogoroto", "+62-896-6807-8464", "https://wa.me/6289668078464?text=Hi, Is any one Available?"));
        models.add(new DataAnggotaModel(R.drawable.person, "Septa Anggi Herlinda", "10 September 2006", "septalinda10@gmail.com", "PAC Kabuh", "+62-815-5399-5206", "https://wa.me/6281553995206?text=Hi, Is any one Available?"));
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