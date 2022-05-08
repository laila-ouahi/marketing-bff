package com.cdm.marketingbff.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class ErrorRecord {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    Long id;
    String filePath;
    String motif;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public ErrorRecord(String filePath, String motif) {
        this.filePath = filePath;
        this.motif = motif;
    }

    public ErrorRecord() {
    }
}
