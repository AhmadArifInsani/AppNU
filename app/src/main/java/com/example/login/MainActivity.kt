package com.example.login

import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.login.databinding.HomeBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : HomeBinding
    private lateinit var adapter: ImageSliderAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = HomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.add(
            ImageData(
                "https://i.ibb.co/ggdkHQg/Whats-App-Image-2022-01-30-at-23-33-29.jpg"
            )
        )

        list.add(
            ImageData(
                "https://i.ibb.co/FgfPBZ9/IMG-1464.jpg"
            )
        )

        list.add(
            ImageData(
                "https://i.ibb.co/CzPKSnS/IMG-5775.jpg"
            )
        )

        adapter = ImageSliderAdapter(list)
        binding.viewPager.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })
    }

    private fun selectedDot(position: Int) {
        for (i in 0 until list.size){
            if (i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_primary))
            else
                dots[i].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_secondary))
        }
    }

    private fun setIndicator() {
        for(i in 0 until list.size){
            dots.add(TextView(this))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY). toString()
            } else {
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.indikator.addView(dots[i])
        }
    }
}