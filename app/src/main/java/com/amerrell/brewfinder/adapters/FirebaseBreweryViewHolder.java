package com.amerrell.brewfinder.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amerrell.brewfinder.R;
import com.amerrell.brewfinder.models.Brewery;
import com.squareup.picasso.Picasso;

/**
 * Created by Guest on 12/8/16.
 */
public class FirebaseBreweryViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;

    public FirebaseBreweryViewHolder (View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindBrewery(Brewery brewery) {
        ImageView savedBreweryImageView = (ImageView) mView.findViewById(R.id.savedBreweryListImageView);
        TextView savedBreweryTitleTextView = (TextView) mView.findViewById(R.id.savedBreweryNameTextView);
        TextView savedBreweryAddressTextView = (TextView) mView.findViewById(R.id.savedAddressTextView);
        Picasso.with(mContext).load(brewery.getLogoUrl()).into(savedBreweryImageView);
        savedBreweryTitleTextView.setText(brewery.getName());
        savedBreweryAddressTextView.setText(brewery.getAddress());
    }
}
