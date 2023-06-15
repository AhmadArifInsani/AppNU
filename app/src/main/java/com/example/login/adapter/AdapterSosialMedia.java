package com.example.login.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.login.R;

import java.util.List;

public class AdapterSosialMedia extends RecyclerView.Adapter<AdapterSosialMedia.ViewHolder> {
    List<String> text;
    Context ctx;
    List<Integer> image;
    LayoutInflater inflater;

    public AdapterSosialMedia(Context ctx, List<String> text, List<Integer> image){
        this.ctx = ctx;
        this.text = text;
        this.image= image;
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
        holder.text.setText(text.get(position));
        holder.image.setImageResource(image.get(position));

    }

    @Override
    public int getItemCount() {
        return text.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView text;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.txtJudul);
            image = itemView.findViewById(R.id.imgJudul);
        }
    }
}
