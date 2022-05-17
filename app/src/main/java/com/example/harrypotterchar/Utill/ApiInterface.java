package com.example.harrypotterchar.Utill;

import com.example.harrypotterchar.Model.CharModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("characters")
    Call<List<CharModel>> getCharacter();
}
