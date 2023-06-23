package com.example.login.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.adapter.AdapterAlumni;
import com.example.login.adapter.AdapterDataAnggota;
import com.example.login.model.AlumniModel;
import com.example.login.model.DataAnggotaModel;

import java.util.ArrayList;

public class AlumniActivity extends AppCompatActivity {
    private ImageView ImgBack, ImgHome;
    TextView Title;
    RecyclerView recyclerView;
    ArrayList<AlumniModel> models;
    AdapterAlumni adapterAlumni;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni);
        ImgBack = findViewById(R.id.ibBack);
        ImgHome = findViewById(R.id.ivHomeButton);
        Title = findViewById(R.id.tvTitle);
        recyclerView = findViewById(R.id.rvAlumni);
        searchView = findViewById(R.id.searchAlumni);

        ImgBack.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });
        ImgHome.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(), HomeActivity.class));
        });

        Title.setText("Data Alumni");
        models = new ArrayList<>();
        searchView.clearFocus();

        models.add(new AlumniModel(R.drawable.person, "Kholisotin, S. Pd. I.", "06 Februari 1972", "kholisotin06@gmail.com", "PAC Sumobito",  "+62-856-4855-1943", "1996-2002", "https://wa.me/6285648551943?text=Hi,Is any one Available?"));
        models.add(new AlumniModel(R.drawable.person, "Wakiatur Rochma", "12 Mei 1989", "wakiaturrochma@gmail.com", "PAC Sumobito",  "+62-812-1682-7762", "2010-2012", "https://wa.me/6281216827762?text=Hi,Is any one Available?"));
        models.add(new AlumniModel(R.drawable.person, "Munzilah", "15 Januari 1975", "munzilah@gmail.com", "PAC Sumobito", "+62-856-0762-4258", "1994-1996", "https://wa.me/6285607624258?text=Hi,Is any one Available?"));
        models.add(new AlumniModel(R.drawable.person, "Imam Hambali", "06 Mei 1972", "imamhambali@gmail.com", "PAC Sumobito", "+62-856-0733-3539", "1994-1996", "https://wa.me/6285607333539?text=Hi,Is any one Available?"));
        models.add(new AlumniModel(R.drawable.person, "Muslik", "19 Oktober 1977", "muslik19@gmail.com", "PAC Sumobito", "+62-856-4896-8095", "2000-2003", "https://wa.me/6285648968095?text=Hi,Is any one Available?"));
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
        adapterAlumni = new AdapterAlumni(this, models);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(adapterAlumni);
    }
    private void filterList(String text){
        ArrayList<AlumniModel> filteredList = new ArrayList<>();
        for (AlumniModel item : models){
            if (item.getNama().toLowerCase().contains(text.toLowerCase())){
                filteredList.add(item);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(this, "No data found", Toast.LENGTH_SHORT).show();
        }else{
            adapterAlumni.setFilteredList(filteredList);
        }
    }
}