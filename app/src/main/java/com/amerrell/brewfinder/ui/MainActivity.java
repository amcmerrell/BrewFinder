package com.amerrell.brewfinder.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.amerrell.brewfinder.Constants;
import com.amerrell.brewfinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.breweryTitleTextView) TextView mAppTitleView;
    @Bind(R.id.viewBreweriesButton) Button mViewBreweriesButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.aboutButton) Button mAboutButton;
    @Bind(R.id.savedBreweriesButton) Button mSavedBreweriesButton;

    private SharedPreferences mSharedPrefences;
    private SharedPreferences.Editor mEditor;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    getSupportActionBar().setTitle("Cheers, " + user.getDisplayName());
                } else {

                }
            }
        };

        Typeface goodDog = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.ttf");
        mAppTitleView.setTypeface(goodDog);

        mSharedPrefences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPrefences.edit();

        mViewBreweriesButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
        mSavedBreweriesButton.setOnClickListener(this);
    }

    private void addToSharedPreferences(String zipCode) {
        mEditor.putString(Constants.PREFERENCES_ZIPCODE_KEY, zipCode).apply();
    }

    @Override
    public void onClick(View v) {
        if (v == mViewBreweriesButton) {
            String zipCode = mLocationEditText.getText().toString();
            if (!(zipCode.length() == 5)) {
                Toast.makeText(MainActivity.this, "Please enter a 5 digit zip code.", Toast.LENGTH_SHORT).show();
                return;
            }
            addToSharedPreferences(zipCode);
            Intent intent = new Intent(MainActivity.this, BreweryListActivity.class);
            startActivity(intent);
        } else if (v == mAboutButton) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            startActivity(intent);
        } else if (v == mSavedBreweriesButton) {
            Intent intent = new Intent(MainActivity.this, SavedBreweriesActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
