package com.example.login.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.login.R
import com.example.login.adapter.BeritaAdapter
import com.example.login.databinding.ActivityBeritaBinding
import com.example.login.model.BeritaModel

class BeritaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBeritaBinding
    private lateinit var dataBerita: ArrayList<BeritaModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolbar()
        getDataBerita()
        initRecyclerView()
        initAction()
    }

    private fun getDataBerita() {
        dataBerita = ArrayList()
        dataBerita.add(
            BeritaModel(
                "1",
                "Title 1",
                "Thumbnail 1",
                "Detail 1",
                "Writer 1",
                "dateCreated 1"
            )
        )
        dataBerita.add(
            BeritaModel(
                "2",
                "Title 2",
                "Thumbnail 2",
                "Detail 2",
                "Writer 2",
                "dateCreated 2"
            )
        )
        dataBerita.add(
            BeritaModel(
                "3",
                "Title 3",
                "Thumbnail 3",
                "Detail 3",
                "Writer 3",
                "dateCreated 3"
            )
        )
        dataBerita.add(
            BeritaModel(
                "4",
                "Title 4",
                "Thumbnail 4",
                "Detail 4",
                "Writer 4",
                "dateCreated 4"
            )
        )
        dataBerita.add(
            BeritaModel(
                "5",
                "Title 51",
                "Thumbnail 52",
                "Detail 65",
                "Writer 53",
                "dateCreated 54"
            )
        )
        dataBerita.add(
            BeritaModel(
                "56",
                "Title 35",
                "Thumbnail 52",
                "Detail 54",
                "Writer 55",
                "dateCreated 51"
            )
        )
        dataBerita.add(
            BeritaModel(
                "51",
                "Title 65",
                "Thumbnail 315",
                "Detail 25",
                "Writer 51",
                "dateCreated 52"
            )
        )
        dataBerita.add(
            BeritaModel(
                "52",
                "Title 53",
                "Thumbnail 54",
                "Detail 55",
                "Writer 54",
                "dateCreated 212"
            )
        )
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

    private fun initRecyclerView() {
        with(binding.rvBerita) {
            val beritaAdapter = BeritaAdapter(context, dataBerita)
            adapter = beritaAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }
    }
}