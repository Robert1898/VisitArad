package com.example.visitarad.evenimente;

import com.example.visitarad.ModelLocatii;

public class ModelEvenimente {

    private String despreEveniment;
    private String urlBilete,nume;
    private double latitudine,longitudine;

    public ModelEvenimente(String nume,String despreEveniment, String urlBilete, double latitudine, double longitudine) {
        this.nume = nume;
        this.despreEveniment = despreEveniment;
        this.urlBilete = urlBilete;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    public String getDespreEveniment() {
        return despreEveniment;
    }

    public String getNume() {
        return nume;
    }

    public String getUrlBilete() {
        return urlBilete;
    }

    public double getLatitudine() {
        return latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }
}
