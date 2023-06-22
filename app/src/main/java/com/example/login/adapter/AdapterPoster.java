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
    public interface ItemClickListener{
        void onItemClick(View v, int pos);
    }

    public AdapterPoster(Context ctx, List<PosterModelAdmin> models){
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PosterModelAdmin posterModelAdmin = models.get(position);
        holder.deskripsi.setText(posterModelAdmin.getDeskripsi());
        Glide.with(ctx).load(posterModelAdmin.getImage()).into(holder.image);
        holder.image.setOnClickListener(view -> {
            Intent intent = new Intent(ctx, DetailPoster.class);
            intent.putExtra("PosterModel", posterModelAdmin);
            ctx.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView deskripsi;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            deskripsi = itemView.findViewById(R.id.tvJudulPoster);
            image = itemView.findViewById(R.id.ivJudulPoster);
        }
    }
}
