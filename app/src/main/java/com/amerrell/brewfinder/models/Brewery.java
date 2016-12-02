package com.amerrell.brewfinder.models;

/**
 * Created by Guest on 12/2/16.
 */
public class Brewery {
    private String mName;
    private String mLogoUrl;
    private String mPhone;
    private String mWebsite;
    private String mAddress;
    private double mLatitude;
    private double mLongitude;

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
