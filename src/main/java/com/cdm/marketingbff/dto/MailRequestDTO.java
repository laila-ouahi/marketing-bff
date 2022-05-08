package com.cdm.marketingbff.dto;

public class MailRequestDTO {
    String filePath;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public MailRequestDTO(String filePath) {
        this.filePath = filePath;
    }

    public MailRequestDTO() {
    }
}
