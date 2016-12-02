package com.amerrell.brewfinder.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.amerrell.brewfinder.R;
import com.amerrell.brewfinder.models.Brewery;
import com.amerrell.brewfinder.services.BreweryDBService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BreweryListActivity extends AppCompatActivity {
    public static final String TAG = BreweryListActivity.class.getSimpleName();

    public ArrayList<Brewery> mBreweries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_list);
        ButterKnife.bind(this);

        Typeface goodDog = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.ttf");
        //mBeerListTitleView.setTypeface(goodDog);

        Intent intent = getIntent();
        String zipCode = intent.getStringExtra("zipCode");
        getBreweries(zipCode);
    }

    private void getBreweries(String location) {
        final BreweryDBService breweryDBService = new BreweryDBService();
        breweryDBService.findBreweries(location, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.isSuccessful()) {
                    mBreweries = breweryDBService.processResults(response);
                }
            }
        });
    }
}
