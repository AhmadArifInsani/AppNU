package com.example.login.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.activity.BeritaActivity
import com.example.login.activity.DetailBeritaActivity
import com.example.login.databinding.ItemBeritaBinding
import com.example.login.model.BeritaModel

class BeritaAdapter(private val context: Context, private val dataBerita: List<BeritaModel>) :
    RecyclerView.Adapter<BeritaAdapter.ViewHolder>() {

    class ViewHolder(itemBinding: ItemBeritaBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val ivThumbnail = itemBinding.ivThumbnail
        val tvTitle = itemBinding.tvTitle
        val cvContainer = itemBinding.cvContainer
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemBeritaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return dataBerita.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val beritaModel = dataBerita[position]
        //TODO: ganti gambar pake glide ambil url file di firebase storage
        holder.ivThumbnail.setImageResource(R.drawable.image_2)
        holder.tvTitle.text = beritaModel.title
        holder.cvContainer.setOnClickListener {
            val intent = Intent(context, DetailBeritaActivity::class.java)
            intent.putExtra("beritaModel", beritaModel)
            context.startActivity(intent)
        }
    }
}