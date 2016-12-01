package com.amerrell.brewfinder;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Andrew on 11/30/2016.
 */
public class BreweryDBService {
    public static void findBreweries(String zipCode, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BREWERYDB_LOCATION_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.BREWERYDB_KEY_PARAMETER, Constants.BREWERYDB_API_KEY)
                .addQueryParameter(Constants.BREWERYDB_ZIPCODE_PARAMETER, zipCode);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
