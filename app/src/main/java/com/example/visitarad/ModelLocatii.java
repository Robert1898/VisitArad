package com.example.visitarad;

public class ModelLocatii {

    private String id;
    private String name;
    private String descriere;
    private double latitude;
    private double longitude;

    private String facebook,insta,site,mail,contact;

    public ModelLocatii(String id, String name, String descriere, double latitude, double longitude, String facebook, String insta, String site, String mail, String contact) {
        this.id = id;
        this.name = name;
        this.descriere = descriere;
        this.latitude = latitude;
        this.longitude = longitude;
        this.facebook = facebook;
        this.insta = insta;
        this.site = site;
        this.mail = mail;
        this.contact = contact;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getInsta() {
        return insta;
    }

    public String getSite() {
        return site;
    }

    public String getMail() {
        return mail;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescriere() {
        return descriere;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getContact() {
        return contact;
    }
}
