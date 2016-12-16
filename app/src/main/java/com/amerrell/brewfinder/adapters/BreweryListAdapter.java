package com.amerrell.brewfinder.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
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

/**
 * Created by Guest on 12/2/16.
 */
public class BreweryListAdapter extends RecyclerView.Adapter<BreweryListAdapter.BreweryViewHolder> {
    private ArrayList<Brewery> mBreweries = new ArrayList<>();
    private Context mContext;

    public BreweryListAdapter(Context context, ArrayList<Brewery> breweries) {
        mContext = context;
        mBreweries = breweries;
    }

    @Override
    public BreweryListAdapter.BreweryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.brewery_card_item, parent, false);
        BreweryViewHolder viewHolder = new BreweryViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BreweryViewHolder holder, int position) {
        holder.mBreweryNameTextView.setText(mBreweries.get(position).getName());
        holder.mAddressTextView.setText(mBreweries.get(position).getAddress());
        Picasso.with(mContext).load(mBreweries.get(position).getLogoUrl()).into(holder.mBreweryListImageView);
    }

    @Override
    public int getItemCount() {
        return mBreweries.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public class BreweryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mBreweryListImageView;
        TextView mBreweryNameTextView;
        TextView mAddressTextView;
        CardView mCardView;

        private Context mContext;

        public BreweryViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView)itemView.findViewById(R.id.breweryCardView);
            mBreweryListImageView = (ImageView)itemView.findViewById(R.id.breweryListImageView);
            mBreweryNameTextView = (TextView)itemView.findViewById(R.id.breweryNameTextView);
            mAddressTextView = (TextView)itemView.findViewById(R.id.addressTextView);
            itemView.setOnClickListener(this);

            mContext = itemView.getContext();
        }

//        public void bindBrewery(Brewery brewery) {
//            mBreweryNameTextView.setText(brewery.getName());
//            mAddressTextView.setText(brewery.getAddress());
//            Picasso.with(mContext).load(brewery.getLogoUrl()).into(mBreweryListImageView);
//        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(mContext, BreweryDetailsActivity.class);
            int position = getAdapterPosition();
            intent.putExtra("position", position);
            intent.putExtra("breweries", Parcels.wrap(mBreweries));
            mContext.startActivity(intent);
        }
    }
}
