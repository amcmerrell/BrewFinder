package com.amerrell.brewfinder.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.amerrell.brewfinder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.breweryTitleTextView) TextView mAppTitleView;
    @Bind(R.id.viewBreweriesButton) Button mViewBreweriesButton;
    @Bind(R.id.locationEditText) EditText mLocationEditText;
    @Bind(R.id.aboutButton) Button mAboutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface goodDog = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.ttf");
        mAppTitleView.setTypeface(goodDog);

        mViewBreweriesButton.setOnClickListener(this);
        mAboutButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mViewBreweriesButton) {
            String zipCode = mLocationEditText.getText().toString();
            Intent intent = new Intent(MainActivity.this, BreweryListActivity.class);
            intent.putExtra("zipCode", zipCode);

            startActivity(intent);
        } else if (v == mAboutButton) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);

            startActivity(intent);
        }
    }
}
