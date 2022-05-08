package com.cdm.marketingbff.dto;

public class MailResponseDTO {
    String code;
    String statut;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public MailResponseDTO(String code, String statut) {
        this.code = code;
        this.statut = statut;
    }

    public MailResponseDTO() {
    }
}
