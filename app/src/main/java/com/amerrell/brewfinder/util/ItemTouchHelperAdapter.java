package com.amerrell.brewfinder.util;

/**
 * Created by Andrew on 12/16/2016.
 */
public interface ItemTouchHelperAdapter {
    boolean onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
