package com.amerrell.brewfinder.adapters;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amerrell.brewfinder.R;
import com.amerrell.brewfinder.models.Brewery;
import com.amerrell.brewfinder.util.ItemTouchHelperViewHolder;
import com.squareup.picasso.Picasso;

/**
 * Created by Guest on 12/8/16.
 */
public class FirebaseBreweryViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder {
    View mView;
    Context mContext;
    public ImageView mSavedBreweryImageView;

    public FirebaseBreweryViewHolder (View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindBrewery(Brewery brewery) {
        mSavedBreweryImageView = (ImageView) mView.findViewById(R.id.savedBreweryListImageView);
        TextView savedBreweryTitleTextView = (TextView) mView.findViewById(R.id.savedBreweryNameTextView);
        TextView savedBreweryAddressTextView = (TextView) mView.findViewById(R.id.savedAddressTextView);
        Picasso.with(mContext).load(brewery.getLogoUrl()).into(mSavedBreweryImageView);
        savedBreweryTitleTextView.setText(brewery.getName());
        savedBreweryAddressTextView.setText(brewery.getAddress());
    }

    @Override
    public void onItemSelected() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.drag_scale_on);
        set.setTarget(itemView);
        set.start();
    }

    @Override
    public void onItemClear() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.drag_scale_off);
        set.setTarget(itemView);
        set.start();
    }
}
