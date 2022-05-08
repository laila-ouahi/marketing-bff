package com.cdm.marketingbff.service;

import com.cdm.marketingbff.dto.FileResponseDTO;
import com.cdm.marketingbff.dto.MailResponseDTO;
import com.cdm.marketingbff.model.Notification;
import java.io.IOException;
import java.util.List;

public interface NotificationService {
    public List<Notification> read(String filePath) throws IOException;
    public List<FileResponseDTO> checkFiles(String directoryPath);
    public List<FileResponseDTO> checkSentFiles();
    public List<FileResponseDTO> checkErrorFiles();
    public void sendMail(String filePath) throws IOException;
}
