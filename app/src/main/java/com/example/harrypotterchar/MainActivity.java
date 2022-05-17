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
import com.todkars.shimmer.ShimmerRecyclerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager layoutManager;
    private CharAdapter charAdapter;
    private List<CharModel> charModelList;
    private ApiInterface apiInterface;

    private ShimmerRecyclerView mShimmerRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


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

                Toast.makeText(MainActivity.this, "Error : "+ t.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}