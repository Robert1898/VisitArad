package com.example.visitarad.hoteluri;

import com.example.visitarad.ModelLocatii;

public class ModelHotel extends ModelLocatii {

    private String facilitati;

    public ModelHotel(String id, String name, String descriere, double latitude, double longitude, String facebook, String insta, String site, String mail, String contact, String facilitati) {
        super(id, name, descriere, latitude, longitude, facebook, insta, site, mail, contact);
        this.facilitati = facilitati;
    }

    public String getFacilitati() {
        return facilitati;
    }
}
