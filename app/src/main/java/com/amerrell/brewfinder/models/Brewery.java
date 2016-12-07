package com.amerrell.brewfinder.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/2/16.
 */

@Parcel
public class Brewery {
    String mName;
    String mLogoUrl;
    String mPhone;
    String mWebsite;
    String mAddress;
    double mLatitude;
    double mLongitude;

    public Brewery() {}

    public Brewery(String name, String logoUrl, String phone, String website, String address, double latitude, double longitude) {
        this.mName = name;
        this.mLogoUrl = logoUrl;
        this.mPhone = phone;
        this.mWebsite = website;
        this.mAddress = address;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
    }

    public String getLogoUrl() {
        return mLogoUrl;
    }

    public String getName() {
        return mName;
    }

    public String getPhone() {
        return mPhone;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public String getAddress() {
        return mAddress;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }
}
