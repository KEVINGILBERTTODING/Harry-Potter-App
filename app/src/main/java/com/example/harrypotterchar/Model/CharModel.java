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
    @SerializedName("species")
    private String species;
    @SerializedName("gender")
    private String gender;
    @SerializedName("house")
    private String house;
    @SerializedName("ancestry")
    private String ancestry;
    @SerializedName("eyeColour")
    private String eyeColour;
    @SerializedName("hairColour")
    private String hairColour;
    @SerializedName("patronus")
    private String patronus;



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

    public String getSpecies() {
        return species;
    }

    public String getGender() {
        return gender;
    }

    public String getHouse() {
        return house;
    }

    public String getAncestry() {
        return ancestry;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public String getHairColour() {
        return hairColour;
    }

    public String getPatronus() {
        return patronus;
    }
}
