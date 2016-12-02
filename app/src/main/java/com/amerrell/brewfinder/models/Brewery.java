package com.amerrell.brewfinder.models;

/**
 * Created by Guest on 12/2/16.
 */
public class Brewery {
    private String mName;
    private String mLogoUrl;
    private String mPhone;
    private String mWebsite;

    public Brewery(String name, String logoUrl, String phone, String website) {
        this.mName = name;
        this.mLogoUrl = logoUrl;
        this.mPhone = phone;
        this.mWebsite = website;
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
}
