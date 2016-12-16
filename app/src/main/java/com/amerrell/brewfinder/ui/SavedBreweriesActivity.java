package com.amerrell.brewfinder.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.widget.TextView;

import com.amerrell.brewfinder.Constants;
import com.amerrell.brewfinder.R;
import com.amerrell.brewfinder.adapters.FirebaseBreweryListAdapter;
import com.amerrell.brewfinder.adapters.FirebaseBreweryViewHolder;
import com.amerrell.brewfinder.models.Brewery;
import com.amerrell.brewfinder.util.OnStartDragListener;
import com.amerrell.brewfinder.util.SimpleItemTouchHelperCallback;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedBreweriesActivity extends AppCompatActivity implements OnStartDragListener{
    private Query mBreweryReference;
    private FirebaseBreweryListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Bind(R.id.savedBreweryRecyclerView) RecyclerView mSavedBreweryRecyclerView;
    @Bind(R.id.savedBreweriesTitleTextView) TextView mSavedBreweriesTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_breweries);
        ButterKnife.bind(this);

        Typeface goodDog = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.ttf");
        mSavedBreweriesTitleTextView.setTypeface(goodDog);

        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        mBreweryReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_BREWERIES)
                .orderByChild("pushId")
                .equalTo(uid);

        mFirebaseAdapter = new FirebaseBreweryListAdapter(Brewery.class, R.layout.saved_brewery_card_item, FirebaseBreweryViewHolder.class, mBreweryReference, this, this);
        mSavedBreweryRecyclerView.setHasFixedSize(true);
        mSavedBreweryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSavedBreweryRecyclerView.setAdapter(mFirebaseAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mSavedBreweryRecyclerView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.cleanup();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
