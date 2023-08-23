package com.example.visitarad.restaurante;

import com.example.visitarad.ModelLocatii;

import java.util.ArrayList;

public class ModelRestaurant extends ModelLocatii {
    private String orar;
    private ArrayList<String> imageUrlList;

    //private String imagineCardView;


    public ModelRestaurant(String id, String name, String descriere, double latitude, double longitude, String facebook, String insta, String site, String mail, String contact, String orar, ArrayList<String> imageUrlList) {
        super(id, name, descriere, latitude, longitude, facebook, insta, site, mail, contact);
        this.orar = orar;
        this.imageUrlList = imageUrlList;
    }

    public ArrayList<String> getImageUrlList() {
        return imageUrlList;
    }

    public String getOrar() {
        return orar;
    }


}
