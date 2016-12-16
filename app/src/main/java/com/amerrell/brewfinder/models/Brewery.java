package com.amerrell.brewfinder.models;

import org.parceler.Parcel;

/**
 * Created by Guest on 12/2/16.
 */

@Parcel
public class Brewery {
    String name;
    String index;
    private String pushId;
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
        this.index = "not_specified";
        this.description = "";
        this.logoUrl = logoUrl;
        this.phone = phone;
        this.website = website;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }



    public String getName() {
        return name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getDescription() {
        return description;
    }

    public String getLogoUrl() {
        return logoUrl;
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
