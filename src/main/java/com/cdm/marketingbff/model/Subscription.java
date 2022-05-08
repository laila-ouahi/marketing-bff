package com.cdm.marketingbff.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Subscription {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    Long id;
    String email;
    String motif;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern="yyyy-MM-dd")
    LocalDateTime dateModification;
    boolean isSubscribed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getDateModification() {
        return dateModification;
    }

    public void setDateModification(LocalDateTime dateModification) {
        this.dateModification = dateModification;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public void setSubscribed(boolean subscribed) {
        isSubscribed = subscribed;
    }

    public Subscription(String email, String motif, LocalDateTime dateModification, boolean isSubscribed) {
        this.email = email;
        this.motif = motif;
        this.dateModification = dateModification;
        this.isSubscribed = isSubscribed;
    }

    public Subscription() {
    }
}
