package com.amerrell.brewfinder.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.amerrell.brewfinder.Constants;
import com.amerrell.brewfinder.R;
import com.amerrell.brewfinder.adapters.FirebaseBreweryViewHolder;
import com.amerrell.brewfinder.models.Brewery;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedBreweriesActivity extends AppCompatActivity {
    private DatabaseReference mBreweryReference;
    private FirebaseRecyclerAdapter mFirebaseAdapter;

    @Bind(R.id.savedBreweryRecyclerView) RecyclerView mSavedBreweryRecyclerView;
    @Bind(R.id.savedBreweriesTitleTextView) TextView mSavedBreweriesTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_breweries);
        ButterKnife.bind(this);

        Typeface goodDog = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.ttf");
        mSavedBreweriesTitleTextView.setTypeface(goodDog);

        mBreweryReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_BREWERIES);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        mFirebaseAdapter = new FirebaseRecyclerAdapter<Brewery, FirebaseBreweryViewHolder>(Brewery.class, R.layout.saved_brewery_list_item, FirebaseBreweryViewHolder.class, mBreweryReference) {
            @Override
            protected void populateViewHolder(FirebaseBreweryViewHolder viewHolder, Brewery model, int position) {
                viewHolder.bindBrewery(model);
            }
        };
        mSavedBreweryRecyclerView.setHasFixedSize(true);
        mSavedBreweryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSavedBreweryRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }
}
