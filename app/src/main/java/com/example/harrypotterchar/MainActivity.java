package com.example.harrypotterchar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.harrypotterchar.Adapter.CharAdapter;
import com.example.harrypotterchar.Model.CharModel;
import com.example.harrypotterchar.Utill.ApiInterface;
import com.example.harrypotterchar.Utill.DataApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private CharAdapter charAdapter;
    private List<CharModel> charModelList;
    private ApiInterface apiInterface;


    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inisialisasi recyclerview
        recyclerView = findViewById(R.id.recycler_char);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        apiInterface = DataApi.getDataApi().create(ApiInterface.class);

        loadChar();


    }


    public void loadChar(){
        Call<List<CharModel>> call = apiInterface.getCharacter();
        call.enqueue(new Callback<List<CharModel>>() {
            @Override
            public void onResponse(Call<List<CharModel>> call, Response<List<CharModel>> response) {

                charModelList = response.body();
                charAdapter = new CharAdapter(MainActivity.this, charModelList);
                recyclerView.setAdapter(charAdapter);
            }


            @Override
            public void onFailure(Call<List<CharModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Error : "+ t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}