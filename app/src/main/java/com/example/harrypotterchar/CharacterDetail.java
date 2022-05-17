package com.example.harrypotterchar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CharacterDetail extends AppCompatActivity {


    TextView tvName, tvActor, tvDate;
    ImageView image;

    // String for intent

    String nmChar, actrChar, dateChar, imgChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        tvName      = findViewById(R.id.det_name);
        tvActor     = findViewById(R.id.det_actor);
        tvDate      = findViewById(R.id.det_date_of_birth);
        image       = findViewById(R.id.det_image);




        // get data using intent

        Intent intent   = getIntent();
        this.nmChar     = intent.getStringExtra("name");
        this.actrChar   = intent.getStringExtra("actor");
        this.dateChar   = intent.getStringExtra("date");
        this.imgChar    = intent.getStringExtra("image");

        // set data

        tvName.setText(nmChar);
        tvActor.setText(actrChar);
        tvDate.setText(dateChar);

        // Load image using glide

        Glide.with(CharacterDetail.this)
                .load(imgChar)
                .into(image);



    }
}