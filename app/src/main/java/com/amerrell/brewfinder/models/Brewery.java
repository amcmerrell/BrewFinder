package com.amerrell.brewfinder.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/2/16.
 */

@Parcel
public class Brewery {
    String name;
    String description;
    String logoUrl;
    String phone;
    String website;
    String address;
    double latitude;
    double longitude;

    public Brewery() {}

    public Brewery(String name, String logoUrl, String phone, String website, String address, double latitude, double longitude) {
        this.name = name;
        this.description = "";
        this.logoUrl = logoUrl;
        this.phone = phone;
        this.website = website;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getWebsite() {
        return website;
    }

    public String getAddress() {
        return address;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
