package com.example.visitarad.magazine;

import com.example.visitarad.ModelLocatii;

public class ModelMagazin extends ModelLocatii {
    private String orar;
    public ModelMagazin(String id, String name, String descriere,String orar, double latitude, double longitude, String facebook, String insta, String site, String mail, String contact) {
        super(id, name, descriere, latitude, longitude, facebook, insta, site, mail, contact);
        this.orar = orar;
    }

    public String getOrar() {
        return orar;
    }
}
