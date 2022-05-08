package com.cdm.marketingbff.controller;

import com.cdm.marketingbff.dto.FileResponseDTO;
import com.cdm.marketingbff.dto.MailRequestDTO;
import com.cdm.marketingbff.model.Notification;
import com.cdm.marketingbff.service.NotificationService;
import com.cdm.marketingbff.utils.SendMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/cdm/marketing/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Value("${sent.file.path}")
    String sentFilePath;

    @Value("${error.file.path}")
    String errorFilePath;

    @Value("${available.file.path}")
    String availableFilePath;

    @GetMapping("/check/available")
    ResponseEntity<List<FileResponseDTO>> checkAvailable(){
        List<FileResponseDTO> checkedFiles = notificationService.checkFiles(availableFilePath);
        return  ResponseEntity.ok(checkedFiles);
    }

    @GetMapping("/check/sent")
    ResponseEntity<List<FileResponseDTO>> checkSent(){
        List<FileResponseDTO> sentFiles = notificationService.checkSentFiles();
        return  ResponseEntity.ok(sentFiles);
    }

    @GetMapping("/check/error")
    ResponseEntity<List<FileResponseDTO>> checkError(){
        List<FileResponseDTO> errorFiles = notificationService.checkErrorFiles();
        return  ResponseEntity.ok(errorFiles);
    }

    @PostMapping("/read")
    ResponseEntity<List<Notification>> readFile(@RequestBody MailRequestDTO mailRequestDTO) throws IOException {
        List<Notification> notifications = notificationService.read(mailRequestDTO.getFilePath());
        return  ResponseEntity.ok(notifications);
    }

    @PostMapping("/send")
    ResponseEntity<HttpStatus> send(@RequestBody MailRequestDTO mailRequestDTO) throws IOException {
        notificationService.sendMail(mailRequestDTO.getFilePath());
        return ResponseEntity.ok(HttpStatus.ACCEPTED);
    }

}
