package com.amerrell.brewfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BrewDetailsActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.beerTitleView) TextView mBeerTitleView;
    @Bind(R.id.beerDescriptionView) TextView mBeerDescriptionView;
    @Bind(R.id.noteEditText) EditText mNoteEditText;
    @Bind(R.id.beerNotesView) TextView mBeerNotesView;
    @Bind(R.id.addNoteButton) Button mAddNoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brew_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String beerName = intent.getStringExtra("beerName");
        String beerDescription =intent.getStringExtra("beerDescription");

        mBeerTitleView.setText(beerName);
        mBeerDescriptionView.setText(beerDescription);

        mAddNoteButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mAddNoteButton) {
            String note = mNoteEditText.getText().toString();
            if (note.length() > 0) {
                mBeerNotesView.append(note);
                mNoteEditText.setText("");
            } else {
                Toast.makeText(BrewDetailsActivity.this, "Please add a note before submitting.", Toast.LENGTH_LONG).show();
            }
        }
    }
}
