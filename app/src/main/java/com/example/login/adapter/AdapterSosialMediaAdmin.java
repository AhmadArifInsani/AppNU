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
import com.example.login.model.PosterModelAdmin;

import java.util.List;

public class AdapterSosialMediaAdmin extends RecyclerView.Adapter<AdapterSosialMediaAdmin.ViewHolder> {
    Context context;
    List<PosterModelAdmin> models;
    Dialog dialog;

    public interface Dialog{
        void onClick(int pos);
    }
    public void setDialog(Dialog dialog){
        this.dialog = dialog;
    }
    public AdapterSosialMediaAdmin(Context context, List<PosterModelAdmin> models){
        this.context = context;
        this.models = models;
    }
    @NonNull
    @Override
    public AdapterSosialMediaAdmin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_poster_admin,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSosialMediaAdmin.ViewHolder holder, int position) {
        PosterModelAdmin posterModelAdmin = models.get(position);
        holder.judul.setText(posterModelAdmin.getJudul());
        Glide.with(context).load(models.get(position).getImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView judul;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            judul = itemView.findViewById(R.id.txJudul);
            image = itemView.findViewById(R.id.imgJudul);
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
