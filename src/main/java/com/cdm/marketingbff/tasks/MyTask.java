package com.cdm.marketingbff.tasks;

import com.cdm.marketingbff.dto.FileResponseDTO;
import com.cdm.marketingbff.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.TimerTask;

@Component
public class MyTask {

    public MyTask(){

    }

    @Value("${available.file.path}")
    String directoryPath;

    @Autowired
    NotificationService notificationService;

    @Scheduled(fixedRate = 10000)
    public void run() throws IOException {
        List<FileResponseDTO> fileResponseDTOS = notificationService.checkFiles(directoryPath);
        for (FileResponseDTO file:fileResponseDTOS) {
            notificationService.sendMail(file.getPath());
            System.out.println("fichier de notification mail envoyé" +file.getPath());
        }
    }
}
