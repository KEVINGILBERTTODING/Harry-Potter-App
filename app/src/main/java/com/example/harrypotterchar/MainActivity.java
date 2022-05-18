package com.example.harrypotterchar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
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

    private ShimmerRecyclerView mShimmerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fungsi untuk menyembunyikan navbar

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION|
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        mShimmerRecyclerView = findViewById(R.id.recycler_char);

        mShimmerRecyclerView.setLayoutManager(new LinearLayoutManager(this),
                R.layout.char_list);

        mShimmerRecyclerView.setAdapter(charAdapter);
        // This is optional, use if no attributes are mentioned in layout xml resource.
        // WARNING: Setting Shimmer programmatically will obsolete all shimmer attributes.
        /* mShimmerRecyclerView.setShimmer(mShimmer); */

        /* Shimmer layout view type depending on List / Gird */
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

        // To stimulate long running work using android.os.Handler



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
            Toast.makeText(this, "Tidak ditemukan", Toast.LENGTH_SHORT).show();
        } else {
            charAdapter.filterList(filteredList);
        }



    }






    public void loadChar(){
        Call<List<CharModel>> call = apiInterface.getCharacter();
        call.enqueue(new Callback<List<CharModel>>() {

            @Override
            public void onResponse(Call<List<CharModel>> call, Response<List<CharModel>> response) {

                charModelList = response.body();
                charAdapter = new CharAdapter(MainActivity.this, charModelList);
                mShimmerRecyclerView.setAdapter(charAdapter);
            }


            @Override
            public void onFailure(Call<List<CharModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "No connection, please try again", Toast.LENGTH_LONG).show();

//                Toast.makeText(MainActivity.this, "Error : "+ t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}