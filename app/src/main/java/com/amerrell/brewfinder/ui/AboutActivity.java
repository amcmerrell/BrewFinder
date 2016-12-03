package com.amerrell.brewfinder.ui;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.amerrell.brewfinder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity {
    @Bind(R.id.aboutTitleTextView) TextView mAboutTitleTextView;
    @Bind(R.id.contactTitleTextView) TextView mContactTitleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        Typeface goodDog = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.ttf");
        mAboutTitleTextView.setTypeface(goodDog);
        mContactTitleTextView.setTypeface(goodDog);
    }
}
