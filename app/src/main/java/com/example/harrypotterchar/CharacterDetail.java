package com.example.harrypotterchar;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class CharacterDetail extends AppCompatActivity {


    TextView tvName, tvActor, tvDate;
    ImageView image, profileImg;

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
        profileImg  = findViewById(R.id.profile);




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

        // Load image background using glide

        Glide.with(CharacterDetail.this)
                .load(imgChar)
                // set image blur
                .apply(bitmapTransform(new BlurTransformation(20)))
                .into(image);

        // Load image profile using glide

        Glide.with(CharacterDetail.this)
                .load(imgChar)
                .into(profileImg);


    }
}