package com.example.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;
import com.example.login.model.KaderisasiModel;

import java.util.ArrayList;

public class AdapterKaderisasi extends RecyclerView.Adapter<AdapterKaderisasi.ViewHolder>{
    private final Context context;
    private final ArrayList<KaderisasiModel> models;

    public AdapterKaderisasi(Context context, ArrayList<KaderisasiModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public AdapterKaderisasi.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_kaderisasi,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterKaderisasi.ViewHolder holder, int position) {
        KaderisasiModel kaderisasiModel = models.get(position);
        holder.tvNama.setText(kaderisasiModel.getNmFile());
        holder.btnView.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNama;
        private Button btnView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
