package com.cdm.marketingbff.dto;

public class SubscriptionRequestDTO {
    String email;
    String motif;
    String hash;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public SubscriptionRequestDTO(String email, String motif, String hash) {
        this.email = email;
        this.motif = motif;
        this.hash = hash;
    }

    public SubscriptionRequestDTO() {
    }
}
