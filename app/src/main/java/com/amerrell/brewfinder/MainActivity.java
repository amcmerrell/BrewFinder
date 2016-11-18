package com.amerrell.brewfinder;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.mainBeerTitleView) TextView mMainBeerTitleView;
    @Bind(R.id.viewBrewsButton) Button mViewBrewsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface goodDog = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.ttf");
        mMainBeerTitleView.setTypeface(goodDog);

        mViewBrewsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mViewBrewsButton) {
            Intent intent = new Intent(MainActivity.this, BrewsActivity.class);
            startActivity(intent);
        }
    }
}
