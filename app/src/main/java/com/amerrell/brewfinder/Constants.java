package com.amerrell.brewfinder;

/**
 * Created by Andrew on 11/30/2016.
 */
public class Constants {
    public static final String BREWERYDB_BASE_URL = "http://api.brewerydb.com/v2/";
    public static final String BREWERYDB_API_KEY = BuildConfig.BREWERYDB_API_KEY;

    //Endpoints
    public static final String BREWERYDB_LOCATION_PATH = "locations";

    //Parameters
    public static final String BREWERYDB_KEY_PARAMETER = "key";
    public static final String BREWERYDB_ZIPCODE_PARAMETER = "postalCode";
    public static final String BREWERYDB_CITY_PARAMETER = "locality";
    public static final String BREWERYDB_STATE_PARAMETER = "region";

    //Shared Preferences
    public static final String PREFERENCES_ZIPCODE_KEY = "zipCode";

    //Firebase
    public static final String FIREBASE_CHILD_BREWERIES = "breweries";
}
