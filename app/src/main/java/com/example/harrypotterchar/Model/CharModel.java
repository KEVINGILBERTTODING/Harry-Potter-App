package com.example.harrypotterchar.Model;

import com.google.gson.annotations.SerializedName;

public class CharModel {



    @SerializedName("name")
    private String name;
    @SerializedName("actor")
    private String actor;
    @SerializedName("image")
    private String image;
    @SerializedName("dateOfBirth")
    private String date;

    public String getName() {
        return name;
    }

    public String getActor() {
        return actor;
    }

    public String getDate() {
        return date;
    }

    public String getImage() {
        return image;
    }


}
