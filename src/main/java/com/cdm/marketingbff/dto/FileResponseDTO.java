package com.cdm.marketingbff.dto;

import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;

public class FileResponseDTO {
    String path;
    String dateCreation;
    String dateEnvoi;
    String motif;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public FileResponseDTO() {
    }

    public FileResponseDTO(String path, String dateCreation, String dateEnvoi, String motif) {
        this.path = path;
        this.dateCreation = dateCreation;
        this.dateEnvoi = dateEnvoi;
        this.motif = motif;
    }
}
