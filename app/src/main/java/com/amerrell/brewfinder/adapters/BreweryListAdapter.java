package com.amerrell.brewfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amerrell.brewfinder.R;
import com.amerrell.brewfinder.models.Brewery;
import com.amerrell.brewfinder.ui.BreweryDetailsActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Guest on 12/2/16.
 */
public class BreweryListAdapter extends RecyclerView.Adapter<BreweryListAdapter.BreweryViewHolder> {
    private ArrayList<Brewery> mBreweries = new ArrayList<>();
    private Context mContext;
    private Brewery mBrewery;

    public BreweryListAdapter(Context context, ArrayList<Brewery> breweries) {
        mContext = context;
        mBreweries = breweries;
    }

    @Override
    public BreweryListAdapter.BreweryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brewery_list_item, parent, false);
        BreweryViewHolder viewHolder = new BreweryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BreweryViewHolder holder, int position) {
        holder.bindBrewery(mBreweries.get(position));
        mBrewery = mBreweries.get(position);
    }

    @Override
    public int getItemCount() {
        return mBreweries.size();
    }

    public class BreweryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.breweryImageView) ImageView mBreweryImageView;
        @Bind(R.id.breweryNameTextView) TextView mBreweryNameTextView;
        @Bind(R.id.addressTextView) TextView mAddressTextView;

        private Context mContext;

        public BreweryViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
        }

        public void bindBrewery(Brewery brewery) {
            mBreweryNameTextView.setText(brewery.getName());
            mAddressTextView.setText(brewery.getAddress());
            Picasso.with(mContext).load(brewery.getLogoUrl()).into(mBreweryImageView);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, BreweryDetailsActivity.class);
            //intent.putExtra("position", mPosition);
            intent.putExtra("brewery", Parcels.wrap(mBrewery));
            mContext.startActivity(intent);
        }
    }
}
