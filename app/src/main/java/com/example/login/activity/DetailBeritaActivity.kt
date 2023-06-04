package com.example.login.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.login.R
import com.example.login.databinding.ActivityBeritaBinding
import com.example.login.databinding.ActivityDetailBeritaBinding
import com.example.login.model.BeritaModel

class DetailBeritaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBeritaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initIntent()
        setToolbar()
        initAction()
    }

    private fun initIntent() {
        val beritaModel = intent.getSerializableExtra("beritaModel") as BeritaModel
        with(binding) {
            //TODO: set gambar pake URL dengan glide
            ivThumbnail.setImageResource(R.drawable.image_organisasi_2)
            tvTitle.text = beritaModel.title
            Toast.makeText(this@DetailBeritaActivity, beritaModel.title, Toast.LENGTH_SHORT).show()
            tvDetail.text = beritaModel.detail
            tvWriter.text = beritaModel.writer
            tvCreatedDate.text = beritaModel.dateCreated
        }
    }

    private fun setToolbar() {
        with(binding.tbToolbar) {
            tvTitle.text = "Berita Kegiatan"
            ibBack.setOnClickListener {
                onBackPressed()
            }
        }
    }

    private fun initAction() {
        binding.nvNavBar.ivHomeButton.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}