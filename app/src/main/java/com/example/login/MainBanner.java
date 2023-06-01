package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;

public class MainBanner extends AppCompatActivity {
    ImageSlider imageSlider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_banner);
        imageSlider =findViewById(R.id.image_slider);

        ArrayList<SlideModel> imageList = new ArrayList<>();


        imageList.add(new SlideModel(R.drawable.logo_ipnu,null));
        imageList.add(new SlideModel(R.drawable.logo_ippnu,null));
        imageList.add(new SlideModel(R.drawable.logo_muslimat,null));
        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);
    }
}