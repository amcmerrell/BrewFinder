package com.amerrell.brewfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BrewDetailsActivity extends AppCompatActivity {
    @Bind(R.id.beerTitleView) TextView mBeerTitleView;
    @Bind(R.id.beerDescriptionView) TextView mBeerDescriptionView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brew_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String beerName = intent.getStringExtra("beerName");
        String beerDescription =intent.getStringExtra("beerDescription");

        mBeerTitleView.setText(beerName);
        mBeerDescriptionView.setText(beerDescription);
    }
}
