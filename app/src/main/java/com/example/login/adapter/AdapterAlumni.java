package com.example.login.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.login.R;
import com.example.login.model.AlumniModel;
import com.example.login.model.DataAnggotaModel;

import java.util.ArrayList;

import android.content.Context;

public class AdapterAlumni extends RecyclerView.Adapter<AdapterAlumni.ViewHolder> {
    Context context;
    ArrayList<AlumniModel> models;

    public void setFilteredList(ArrayList<AlumniModel> filteredList){
        this.models = filteredList;
        notifyDataSetChanged();
    }
    public AdapterAlumni(Context context, ArrayList<AlumniModel> models) {
        this.context = context;
        this.models = models;
    }
    @NonNull
    @Override
    public AdapterAlumni.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_alumni,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAlumni.ViewHolder holder, int position) {
        AlumniModel alumniModel = models.get(position);
        Glide.with(context).load(alumniModel.getProfil()).into(holder.profil);
        holder.nama.setText(alumniModel.getNama());
        holder.birthday.setText(alumniModel.getBirthday());
        holder.email.setText(alumniModel.getEmail());
        holder.pimpinan.setText(alumniModel.getPimpinan());
        holder.nomor.setText(alumniModel.getNomor());
        holder.masaJabatan.setText(alumniModel.getMasaJabatan());
        holder.whatsapp.setOnClickListener(view -> {
            String url = "https://wa.me/" + alumniModel.getWhatsapp() + "?text=Hi,Is any one Available?";

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(alumniModel.getWhatsapp()));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nama, birthday, email, pimpinan, nomor, masaJabatan;
        ImageView profil, whatsapp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profil = itemView.findViewById(R.id.profilAlumni);
            nama = itemView.findViewById(R.id.nmAlumni);
            birthday = itemView.findViewById(R.id.birthdayAlumni);
            email = itemView.findViewById(R.id.emailAlumni);
            pimpinan = itemView.findViewById(R.id.pimpinanAlumni);
            nomor = itemView.findViewById(R.id.nomorAlumni);
            masaJabatan = itemView.findViewById(R.id.layout_masa_jabatan);
            whatsapp = itemView.findViewById(R.id.icWhatsappAlumni);
        }
    }
}
