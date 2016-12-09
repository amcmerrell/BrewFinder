package com.amerrell.brewfinder.ui;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.amerrell.brewfinder.Constants;
import com.amerrell.brewfinder.R;
import com.amerrell.brewfinder.adapters.BreweryListAdapter;
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

    @Bind(R.id.breweryRecyclerView) RecyclerView mRecyclerView;
    @Bind(R.id.breweryListTitleTextView) TextView mBreweryListTitleTextView;

    private BreweryListAdapter mAdapter;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentZipCode;

    public ArrayList<Brewery> mBreweries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_list);
        ButterKnife.bind(this);

        Typeface goodDog = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.ttf");
        mBreweryListTitleTextView.setTypeface(goodDog);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentZipCode = mSharedPreferences.getString(Constants.PREFERENCES_ZIPCODE_KEY, null);
        if(mRecentZipCode != null) {
            getBreweries(mRecentZipCode);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                getBreweries(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String zipCode) {
        mEditor.putString(Constants.PREFERENCES_ZIPCODE_KEY, zipCode).apply();
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
                mBreweries = breweryDBService.processResults(response);

                BreweryListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new BreweryListAdapter(getApplicationContext(), mBreweries);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BreweryListActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
