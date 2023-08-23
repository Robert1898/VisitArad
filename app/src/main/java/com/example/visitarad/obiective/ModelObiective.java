package com.example.visitarad.obiective;

import com.example.visitarad.ModelLocatii;

public class ModelObiective extends ModelLocatii {

    private String orar;

    public ModelObiective(String id, String name, String descriere, double latitude, double longitude, String facebook, String insta, String site, String mail, String contact, String orar) {
        super(id, name, descriere, latitude, longitude, facebook, insta, site, mail, contact);
        this.orar = orar;
    }

    public String getOrar() {
        return orar;
    }
}
