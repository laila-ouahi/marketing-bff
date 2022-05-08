package com.cdm.marketingbff.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class SentRecord {
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    Long id;
    String filePath;
    LocalDateTime dateEnvoi;

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

    public LocalDateTime getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(LocalDateTime dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public SentRecord(String filePath, LocalDateTime dateEnvoi) {
        this.filePath = filePath;
        this.dateEnvoi = dateEnvoi;
    }

    public SentRecord() {
    }
}
