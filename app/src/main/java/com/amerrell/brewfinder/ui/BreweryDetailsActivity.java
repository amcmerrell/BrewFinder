package com.amerrell.brewfinder.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.amerrell.brewfinder.Constants;
import com.amerrell.brewfinder.R;
import com.amerrell.brewfinder.models.Brewery;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BreweryDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.breweryDetailsTitleTextView) TextView mBreweryTitleTextView;
    @Bind(R.id.breweryAddressTextView) TextView mBreweryAddressTextView;
    @Bind(R.id.breweryDetailsImageView) ImageView mBreweryDetailsImageView;
    @Bind(R.id.breweryPhoneTextView) TextView mBreweryPhoneTextView;
    @Bind(R.id.saveBreweryButton) Button mSaveBreweryButton;

    private ArrayList<Brewery> mBreweries;
    private Query mDuplicateQuery;
    private ValueEventListener mDuplicateQueryEventListener;
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

        mSaveBreweryButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mSaveBreweryButton) {
            mDuplicateQuery = FirebaseDatabase
                    .getInstance()
                    .getReference()
                    .child(Constants.FIREBASE_CHILD_BREWERIES)
                    .orderByChild("address")
                    .equalTo(mBrewery.getAddress());
            mDuplicateQueryEventListener = mDuplicateQuery.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.getValue() == null) {
                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String uid = user.getUid();
                        mBrewery.setPushId(uid);

                        DatabaseReference breweryRef = FirebaseDatabase
                                .getInstance()
                                .getReference(Constants.FIREBASE_CHILD_BREWERIES);
                        breweryRef.push().setValue(mBrewery);

                        Intent intent = new Intent(BreweryDetailsActivity.this, SavedBreweriesActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(BreweryDetailsActivity.this, "Brewery already saved", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mDuplicateQuery.removeEventListener(mDuplicateQueryEventListener);
    }
}
