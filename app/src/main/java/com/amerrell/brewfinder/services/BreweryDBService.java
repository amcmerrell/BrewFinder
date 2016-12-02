package com.amerrell.brewfinder.services;

import com.amerrell.brewfinder.Constants;
import com.amerrell.brewfinder.models.Brewery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Andrew on 11/30/2016.
 */
public class BreweryDBService {
    public static void findBreweries(String zipCode, Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.BREWERYDB_BASE_URL).newBuilder();
        urlBuilder.addPathSegment(Constants.BREWERYDB_LOCATION_PATH);
        urlBuilder.addQueryParameter(Constants.BREWERYDB_KEY_PARAMETER, Constants.BREWERYDB_API_KEY)
                .addQueryParameter(Constants.BREWERYDB_ZIPCODE_PARAMETER, zipCode);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Brewery> processResults(Response response) {
        ArrayList<Brewery> breweries = new ArrayList<Brewery>();

        try {
            String jsonData = response.body().string();
            if (response.isSuccessful()) {
                JSONObject breweryDbJSON = new JSONObject(jsonData);
                JSONArray dataArray = breweryDbJSON.getJSONArray("data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject breweryJSON = dataArray.getJSONObject(i);
                    String name = breweryJSON.getJSONObject("brewery")
                            .getString("name");
                    String logoUrl = breweryJSON.getJSONObject("brewery")
                            .getJSONObject("images")
                            .getString("squareLarge");
                    String phone = breweryJSON.getString("phone");
                    String website = breweryJSON.getString("website");

                    String streetAddress = breweryJSON.getString("streetAddress");
                    String city = breweryJSON.getString("locality");
                    String state = breweryJSON.getString("region");
                    String zipCode = breweryJSON.getString("postalCode");
                    String address = streetAddress + ", " + city + ", " + state + " " + zipCode;

                    double latitude = breweryJSON.getDouble("latitude");
                    double longitude = breweryJSON.getDouble("longitude");
                    breweries.add(new Brewery(name, logoUrl, phone, website, address, latitude, longitude));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return breweries;
    }
}
