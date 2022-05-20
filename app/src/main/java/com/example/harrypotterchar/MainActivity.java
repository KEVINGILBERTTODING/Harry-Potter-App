package com.example.harrypotterchar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harrypotterchar.Adapter.CharAdapter;
import com.example.harrypotterchar.Model.CharModel;
import com.example.harrypotterchar.Utill.ApiInterface;
import com.example.harrypotterchar.Utill.DataApi;
import com.todkars.shimmer.ShimmerRecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private CharAdapter charAdapter;
    private List<CharModel> charModelList;
    private ApiInterface apiInterface;
    SearchView searchView;
    private TextView tv_username;
    private SwipeRefreshLayout  swipeRefreshLayout;

    private ShimmerRecyclerView mShimmerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fungsi untuk menyembunyikan navbar

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        // Inisialisasi widget Swipe Refresh

        swipeRefreshLayout  =   findViewById(R.id.swipe);

        // Fungsi saat swipe rerfresh

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshItem();
            }
        });

        // Inisialisasi username textview
        tv_username =   findViewById(R.id.username);

        mShimmerRecyclerView = findViewById(R.id.recycler_char);

        mShimmerRecyclerView.setLayoutManager(new LinearLayoutManager(this),
                R.layout.char_list);

        mShimmerRecyclerView.setAdapter(charAdapter);

        mShimmerRecyclerView.setItemViewType((type, position) -> {
            switch (type) {
                case ShimmerRecyclerView.LAYOUT_GRID:
                    return position % 2 == 0
                            ? R.layout.char_list2
                            : R.layout.char_list2;

                default:
                case ShimmerRecyclerView.LAYOUT_LIST:
                    return position == 0 || position % 2 == 0
                            ? R.layout.char_list2
                            : R.layout.char_list2;
            }
        });

        mShimmerRecyclerView.showShimmer();     // to start showing shimmer

        // Inisialisasi recyclerview

        layoutManager = new LinearLayoutManager(this);
        mShimmerRecyclerView.setLayoutManager(layoutManager);
        mShimmerRecyclerView.setHasFixedSize(true);

        apiInterface = DataApi.getDataApi().create(ApiInterface.class);

        loadChar();

        // Inisialisasi searchview

        searchView = findViewById(R.id.search_bar);
        searchView.clearFocus();

        // Fungsi saat memasukkan kata ke dalam searchview

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String querry) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return true;
            }
        });



    }



    // Method untuk swipe refresh

    private void refreshItem() {
        loadChar();

    }


    // Method untuk realtime searchview

    private void filter(String newText) {

        ArrayList<CharModel> filteredList = new ArrayList<>();

        for (CharModel item : charModelList) {
            if (item.getName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(item);

            }
        }


        charAdapter.filterList(filteredList);


        if (filteredList.isEmpty()) {
            Toast.makeText(this, "Not found", Toast.LENGTH_SHORT).show();
        } else {
            charAdapter.filterList(filteredList);
        }



    }



    // Method for load json api

    public void loadChar(){
        Call<List<CharModel>> call = apiInterface.getCharacter();
        call.enqueue(new Callback<List<CharModel>>() {

            @Override
            public void onResponse(Call<List<CharModel>> call, Response<List<CharModel>> response) {

                charModelList = response.body();
                charAdapter = new CharAdapter(MainActivity.this, charModelList);
                mShimmerRecyclerView.setAdapter(charAdapter);
                swipeRefreshLayout.setRefreshing(false);
            }


            @Override
            public void onFailure(Call<List<CharModel>> call, Throwable t) {

                // Menampilkan toast saat no connection

                Toast.makeText(MainActivity.this, "No connection, please try again", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);

//                Toast.makeText(MainActivity.this, "Error : "+ t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}