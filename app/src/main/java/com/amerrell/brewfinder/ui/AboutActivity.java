package com.amerrell.brewfinder.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.amerrell.brewfinder.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.aboutTitleTextView) TextView mAboutTitleTextView;
    @Bind(R.id.contactTitleTextView) TextView mContactTitleTextView;
    @Bind(R.id.contactEmailTextView) TextView mContactEmailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        Typeface goodDog = Typeface.createFromAsset(getAssets(), "fonts/GoodDog.ttf");
        mAboutTitleTextView.setTypeface(goodDog);
        mContactTitleTextView.setTypeface(goodDog);
        mContactEmailTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view == mContactTitleTextView) {
            Intent emailIntent = new Intent(Intent.ACTION_MAIN);
            emailIntent.addCategory(Intent.CATEGORY_APP_EMAIL);
            startActivity(emailIntent);
        }
    }
}
