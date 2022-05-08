package com.cdm.marketingbff.model;

public class Notification {

    String numeroTiers;
    String email;
    String objet;
    String urlImage;
    String lien;

    public String getNumeroTiers() {
        return numeroTiers;
    }

    public void setNumeroTiers(String numeroTiers) {
        this.numeroTiers = numeroTiers;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getLien() {
        return lien;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public Notification(String numeroTiers, String email, String objet, String urlImage, String lien) {
        this.numeroTiers = numeroTiers;
        this.email = email;
        this.objet = objet;
        this.urlImage = urlImage;
        this.lien = lien;
    }

    public Notification() {
    }

    @Override
    public String toString() {
        return "Notification{" +
                "numeroTiers='" + numeroTiers + '\'' +
                ", email='" + email + '\'' +
                ", objet='" + objet + '\'' +
                ", urlImage='" + urlImage + '\'' +
                ", lien='" + lien + '\'' +
                '}';
    }
}
