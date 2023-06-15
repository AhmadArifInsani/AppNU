package com.example.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.login.R;
import com.example.login.model.BeritaModelAdmin;

import java.util.List;

public class AdapterBeritaAdmin extends RecyclerView.Adapter<AdapterBeritaAdmin.ViewHolder> {
    Context context;
    List<BeritaModelAdmin> models;
    Dialog dialog;
    public interface Dialog{
        void onClick(int pos);
    }
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }

    public AdapterBeritaAdmin(Context context, List<BeritaModelAdmin> models) {
        this.context = context;
        this.models = models;
        this.dialog = dialog;
    }

    @NonNull
    @Override
    public AdapterBeritaAdmin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_berita_admin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterBeritaAdmin.ViewHolder holder, int position) {
        BeritaModelAdmin beritaModelAdmin = models.get(position);
        holder.deskripsi.setText(beritaModelAdmin.getDeskripsi());
        Glide.with(context).load(models.get(position).getImage()).into(holder.image);
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
            deskripsi = itemView.findViewById(R.id.tvTitleAdm);
            image = itemView.findViewById(R.id.ivThumbnailAdm);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!= null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }

}
