package com.example.login.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.example.login.R;
import com.example.login.activity.DetailPoster;
import com.example.login.model.PosterModelAdmin;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class AdapterPoster extends RecyclerView.Adapter<AdapterPoster.ViewHolder> {
    Context ctx;
    List<PosterModelAdmin> models;
    LayoutInflater inflater;



    public AdapterPoster(Context ctx, List<PosterModelAdmin> models) {
        this.ctx = ctx;
        this.models = models;
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_sosial_media, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPoster.ViewHolder holder, int position) {
        PosterModelAdmin posterModelAdmin = models.get(position);
        holder.judul.setText(posterModelAdmin.getJudul());
        Glide.with(ctx).load(posterModelAdmin.getImage()).into(holder.image);
        holder.image.setOnClickListener(view -> {
            Intent intent = new Intent(ctx, DetailPoster.class);
            intent.putExtra("PosterModelAdmin", posterModelAdmin);
            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView judul;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.tvJudulPoster);
            image = itemView.findViewById(R.id.ivJudulPoster);
        }
    }
}
