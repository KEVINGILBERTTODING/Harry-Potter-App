package com.example.harrypotterchar;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import jp.wasabeef.glide.transformations.BlurTransformation;

public class CharacterDetail extends AppCompatActivity {


    TextView tvName, tvActor, tvDate, tvSpecies, tvGender, tvHouse, tvAncestry, tvEyeColor, tvHairColor, tvPatronous;
    ImageView image, profileImg;

    // String for intent

    String nmChar, actrChar, dateChar, speciesChar, genderChar, houseChar, eyeChar, hairChar, patronousChar, ancestryChar, imgChar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fungsi untuk menyembunyikan navbar

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setContentView(R.layout.activity_character_detail);
        // Fungsi untuk menyembunyikan status bar



        tvName          = findViewById(R.id.det_name);
        tvActor         = findViewById(R.id.det_actor);
        tvSpecies       = findViewById(R.id.det_species);
        tvDate          = findViewById(R.id.det_date_of_birth);
        tvGender        = findViewById(R.id.det_sex);
        tvHouse         = findViewById(R.id.det_house);
        tvAncestry      = findViewById(R.id.det_ancestry);
        tvEyeColor      = findViewById(R.id.det_eyecolor);
        tvHairColor     = findViewById(R.id.det_haircolor);
        tvPatronous     = findViewById(R.id.det_patronus);
        image           = findViewById(R.id.det_image);
        profileImg      = findViewById(R.id.profile);




        // get data using intent

        Intent intent       = getIntent();
        this.nmChar         = intent.getStringExtra("name");
        this.actrChar       = intent.getStringExtra("actor");
        this.dateChar       = intent.getStringExtra("date");
        this.imgChar        = intent.getStringExtra("image");
        this.speciesChar    = intent.getStringExtra("species");
        this.genderChar     = intent.getStringExtra("gender");
        this.houseChar      = intent.getStringExtra("house");
        this.ancestryChar   = intent.getStringExtra("ancestry");
        this.eyeChar        = intent.getStringExtra("eyecolour");
        this.hairChar       = intent.getStringExtra("haircolour");
        this.patronousChar  = intent.getStringExtra("patronus");

        // set data

        tvName.setText(nmChar);
        tvActor.setText(actrChar);
        tvDate.setText(dateChar);
        tvSpecies.setText(speciesChar);
        tvGender.setText(genderChar);
        tvHouse.setText(houseChar);
        tvAncestry.setText(ancestryChar);
        tvEyeColor.setText(eyeChar);
        tvHairColor.setText(hairChar);
        tvPatronous.setText(patronousChar);

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

    public void btnBack(View view) {
        startActivity(new Intent(CharacterDetail.this, MainActivity.class));
    }
}