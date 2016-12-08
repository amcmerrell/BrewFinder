package com.amerrell.brewfinder.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.amerrell.brewfinder.R;
import com.amerrell.brewfinder.models.Brewery;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BreweryDetailsActivity extends AppCompatActivity {
    @Bind(R.id.breweryDetailsTitleTextView) TextView mBreweryTitleTextView;
    @Bind(R.id.breweryAddressTextView) TextView mBreweryAddressTextView;
    @Bind(R.id.breweryDetailsImageView) ImageView mBreweryDetailsImageView;
    @Bind(R.id.breweryPhoneTextView) TextView mBreweryPhoneTextView;
    private ArrayList<Brewery> mBreweries;
    private Brewery mBrewery;
    private int mPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_details);
        ButterKnife.bind(this);

        mPosition = getIntent().getIntExtra("position", 0);
        mBreweries =  Parcels.unwrap(getIntent().getParcelableExtra("breweries"));

        mBrewery = mBreweries.get(mPosition);
        Typeface goodDog = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.ttf");
        mBreweryTitleTextView.setTypeface(goodDog);

        mBreweryTitleTextView.setText(mBrewery.getName());
        mBreweryAddressTextView.setText(mBrewery.getAddress());
        mBreweryPhoneTextView.setText(mBrewery.getPhone());
        Picasso.with(this).load(mBrewery.getLogoUrl()).into(mBreweryDetailsImageView);
    }
}
