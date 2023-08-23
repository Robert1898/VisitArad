package com.example.visitarad.sanatate;

import com.example.visitarad.ModelLocatii;

public class ModelSanatate extends ModelLocatii {

    private String orar, specializari;

    public ModelSanatate(String id, String name, String descriere, double latitude, double longitude, String facebook, String insta, String site, String mail, String contact, String orar, String specializari) {
        super(id, name, descriere, latitude, longitude, facebook, insta, site, mail, contact);
        this.orar = orar;
        this.specializari = specializari;
    }

    public String getOrar() {
        return orar;
    }

    public String getSpecializari() {
        return specializari;
    }
}
