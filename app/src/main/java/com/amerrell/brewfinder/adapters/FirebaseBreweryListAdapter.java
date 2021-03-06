package com.amerrell.brewfinder.adapters;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.view.View;

import com.amerrell.brewfinder.models.Brewery;
import com.amerrell.brewfinder.util.ItemTouchHelperAdapter;
import com.amerrell.brewfinder.util.OnStartDragListener;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Andrew on 12/16/2016.
 */
public class FirebaseBreweryListAdapter extends FirebaseRecyclerAdapter<Brewery, FirebaseBreweryViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private ChildEventListener mChildEventListener;
    private ArrayList<Brewery> mBreweries = new ArrayList<>();
    private Context mContext;

    public FirebaseBreweryListAdapter(Class<Brewery> modelClass, int modelLayout, Class<FirebaseBreweryViewHolder> viewHolderClass, Query ref, OnStartDragListener onStartDragListener, Context context) {
        super(modelClass, modelLayout, viewHolderClass, ref);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mBreweries.add(dataSnapshot.getValue(Brewery.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setIndexInFirebase() {
        for (Brewery brewery : mBreweries) {
            int index = mBreweries.indexOf(brewery);
            DatabaseReference ref = getRef(index);
            brewery.setIndex(Integer.toString(index));
            ref.setValue(brewery);
        }
    }

    @Override
    protected void populateViewHolder(final FirebaseBreweryViewHolder viewHolder, Brewery model, int position) {
        viewHolder.bindBrewery(model);
        viewHolder.mSavedBreweryImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mBreweries, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return false;
    }

    @Override
    public void onItemDismiss(int position) {
        mBreweries.remove(position);
        getRef(position).removeValue();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }
}
