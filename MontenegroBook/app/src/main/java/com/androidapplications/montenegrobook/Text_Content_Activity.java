package com.androidapplications.montenegrobook;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Text_Content_Activity extends AppCompatActivity {
    private Typeface face1;
    private TextView text_content;
    private ImageView image_content;
    private int category;
    private int position;

    //текстовое описание
    private int[]array_cities = {
            R.string.city_podgorica,
            R.string.city_tivat,
            R.string.city_budva,
            R.string.city_kotor,
            R.string.city_jablyak,
    };
    private int[]array_beaches = {
            R.string.beaches_mogren,
            R.string.beaches_king,
    };
    private int[]array_sight = {
            R.string.trifon_church,
            R.string.sveti_stefan,
            R.string.sight_perast,
            R.string.black_sea,
    };
    private int[]array_food = {
            R.string.chevapcici,
            R.string.giros,
            R.string.fruit,

    };
    private int[]array_price = {
            R.string.price_acomodation,
            R.string.price_for_food,
            R.string.price_for_service
    };
    private int[]array_houses = {
            R.string.apartments,
            R.string.hauses,
    };
    //картинки
    private int[]array_image_cities = {
            R.drawable.podgorica_city,
            R.drawable.tivat_city,
            R.drawable.budva,
            R.drawable.kotor_city,
            R.drawable.jablyak_city,
    };
    private int[]array_image_beaches = {
            R.drawable.mogren_beaches,
            R.drawable.king_beaches,
    };
    private int[]array_image_sight = {
            R.drawable.trifon_sight,
            R.drawable.sveti_stefan_sight,
            R.drawable.perast_sight,
            R.drawable.black_sea_sight,
    };
    private int[]array_image_food = {
            R.drawable.chevapcici_food,
            R.drawable.giros_food,
            R.drawable.fruit_food,
    };
    private int[]array_image_price = {
            R.drawable.acomodation_price,
            R.drawable.food_price,
            R.drawable.price_service,
    };
    private int[]array_image_house = {
            R.drawable.appartmens_houses,
            R.drawable.houses_hauses,
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_description);
        init();
        reciveIntent();
    }



    private void reciveIntent(){
        Intent i = getIntent();
        if(i != null){
            category = i.getIntExtra("category",0);
            position = i.getIntExtra("position", 0);
        }
        switch (category){
            case 0:
                image_content.setImageResource(array_image_cities[position]);
                text_content.setText(array_cities[position]);
                break;
            case 1:
                image_content.setImageResource(array_image_beaches[position]);
                text_content.setText(array_beaches[position]);
                break;
            case 2:
                image_content.setImageResource(array_image_sight[position]);
                text_content.setText(array_sight[position]);
                break;
            case 3:
                image_content.setImageResource(array_image_food[position]);
                text_content.setText(array_food[position]);
                break;
            case 4:
                image_content.setImageResource(array_image_price[position]);
                text_content.setText(array_price[position]);
                break;
            case 5:
                image_content.setImageResource(array_image_house[position]);
                text_content.setText(array_houses[position]);
                break;
        }
    }
    private void init(){
        text_content = findViewById(R.id.text_description);
        image_content = findViewById(R.id.image_content);
        face1 = Typeface.createFromAsset(this.getAssets(), "fonts/oranian.ttf");
        text_content.setTypeface(face1);

    }
}
