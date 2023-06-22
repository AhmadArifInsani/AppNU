package com.example.login.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.login.R;
import com.example.login.activity.DetailBerita;
import com.example.login.model.BeritaModel;

import java.util.List;

public class AdapterBerita extends RecyclerView.Adapter<AdapterBerita.ViewHolder> {
    Context ctx;
    List<BeritaModel> list;
    LayoutInflater inflater;

    public AdapterBerita(Context ctx, List<BeritaModel> list) {
        this.ctx = ctx;
        this.list = list;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public AdapterBerita.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_berita, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBerita.ViewHolder holder, int position) {
        BeritaModel beritaModel = list.get(position);
        holder.judul.setText(beritaModel.getJudul());
        Glide.with(ctx).load(beritaModel.getImage()).into(holder.image);
        holder.detail.setOnClickListener(view -> {
            Intent intent = new Intent(ctx, DetailBerita.class);
            intent.putExtra("BeritaModel", beritaModel);
            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul, detail;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.tvTitle);
            detail = itemView.findViewById(R.id.tvBacaDetailBerita);
            image = itemView.findViewById(R.id.ivThumbnail);
        }
    }
}
