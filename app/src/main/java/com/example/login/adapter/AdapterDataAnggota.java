package com.example.login.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.login.R;
import com.example.login.model.DataAnggotaModel;

import java.util.ArrayList;

import android.content.Context;

public class AdapterDataAnggota extends RecyclerView.Adapter<AdapterDataAnggota.ViewHolder> {
    Context context;
    ArrayList<DataAnggotaModel> models;

    public void setFilteredList(ArrayList<DataAnggotaModel> filteredList){
        this.models = filteredList;
        notifyDataSetChanged();
    }

    public AdapterDataAnggota(Context context, ArrayList<DataAnggotaModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public AdapterDataAnggota.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_data_anggota,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataAnggota.ViewHolder holder, int position) {
        DataAnggotaModel dataAnggotaModel = models.get(position);
        Glide.with(context).load(dataAnggotaModel.getProfil()).into(holder.profil);
        holder.nama.setText(dataAnggotaModel.getNama());
        holder.birthday.setText(dataAnggotaModel.getBirthday());
        holder.email.setText(dataAnggotaModel.getEmail());
        holder.pimpinan.setText(dataAnggotaModel.getPimpinan());
        holder.nomor.setText(dataAnggotaModel.getNomor());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, birthday, email, pimpinan, nomor;
        ImageView profil;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.tvTitle);
            birthday = itemView.findViewById(R.id.tanggal_lahir);
            email = itemView.findViewById(R.id.email);
            pimpinan = itemView.findViewById(R.id.pimpinan);
            nomor = itemView.findViewById(R.id.nomor_whatsapp);
            profil = itemView.findViewById(R.id.ivThumbnail);
        }
    }
}
