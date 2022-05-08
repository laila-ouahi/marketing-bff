package com.cdm.marketingbff.serviceImpl;

import com.cdm.marketingbff.customException.ApiRequestException;
import com.cdm.marketingbff.dto.FileResponseDTO;
import com.cdm.marketingbff.dto.MailResponseDTO;
import com.cdm.marketingbff.model.ErrorRecord;
import com.cdm.marketingbff.model.Notification;
import com.cdm.marketingbff.model.SentRecord;
import com.cdm.marketingbff.model.Subscription;
import com.cdm.marketingbff.repository.ErrorRecordRepository;
import com.cdm.marketingbff.repository.SentRecordRepository;
import com.cdm.marketingbff.repository.SubscriptionRepository;
import com.cdm.marketingbff.service.NotificationService;
import com.cdm.marketingbff.utils.HashMail;
import com.cdm.marketingbff.utils.SendMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileTime;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    final static Logger LOGGER = LoggerFactory.getLogger(NotificationServiceImpl.class);

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private ErrorRecordRepository errorRecordRepository;

    @Autowired
    private SentRecordRepository sentRecordRepository;

    @Value("${sent.file.path}")
    String subDirectoryPath;

    @Value("${error.file.path}")
    String errorDirectoryPath;

    @Value("${client.url}")
    String clientURL;

    @Override
    public List<FileResponseDTO> checkFiles(String directoryPath) {
        File[] files = new File(directoryPath).listFiles();
        List<FileResponseDTO> fileResponseDTOS = new ArrayList<>();
        for (File file : files){
            if(file.isFile() && file.getName().endsWith(".sav")){
                String filePath = directoryPath+"/"+file.getName();
                try {
                    FileTime creationTime = (FileTime) Files.getAttribute(Path.of(filePath), "creationTime");
                    FileResponseDTO fileResponseDTO = new FileResponseDTO(filePath,formatDate(creationTime),null,null);
                    fileResponseDTOS.add(fileResponseDTO);
                } catch (IOException ex) {
                    LOGGER.error("error checking file ",ex.getMessage());
                    throw new ApiRequestException(ex.getMessage());
                }
            }
        }
        return fileResponseDTOS;
    }

    @Override
    public List<FileResponseDTO> checkSentFiles() {
        List<SentRecord> sentRecords =sentRecordRepository.findAll();
        List<FileResponseDTO> fileResponseDTOs = sentRecords.stream().map(
                item-> new FileResponseDTO(item.getFilePath(),null,item.getDateEnvoi().format(DATE_FORMATTER), null)
        ).collect(Collectors.toList());
        return fileResponseDTOs;
    }

    @Override
    public List<FileResponseDTO> checkErrorFiles() {
        List<ErrorRecord> errorRecords =errorRecordRepository.findAll();
        List<FileResponseDTO> fileResponseDTOs = errorRecords.stream().map(
                item-> new FileResponseDTO(item.getFilePath(),null, null,item.getMotif())
        ).collect(Collectors.toList());
        return fileResponseDTOs;
    }

    @Override
    public List<Notification> read(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<Notification> notifications = new ArrayList<>();
        try{
            reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if(parts.length!=5) throw new ApiRequestException("Le fichier ne respecte pas le pattern de split");
                Notification notification = new Notification(parts[0],parts[1],parts[2],parts[3],parts[4]);
                notifications.add(notification);
            }
        }
        catch (IOException e) {
            LOGGER.error("error reading notifications ",e.getMessage());
            throw new ApiRequestException(e.getMessage());
        }
        finally{
            reader.close();
        }
        return notifications;
    }

    public List<Notification> filterNotification(List<Notification> notifications) {
        List<Subscription> souscriptions = subscriptionRepository.findByIsSubscribedFalse();
        Map<String,Subscription> souscriptionsMap = souscriptions.stream().collect(Collectors.toMap(Subscription::getEmail, Function.identity()));
        List<Notification> remaningNotifications = notifications.stream().filter(
                notification -> !souscriptionsMap.containsKey(notification.getNumeroTiers())
        ).collect(Collectors.toList());
        return remaningNotifications;
    }


    @Override
    public void sendMail(String filePath) throws IOException {
        String sender = "lailaouahi11@gmail.com";
        try {
            List<Notification> notifications = filterNotification(read(filePath));
            for (Notification notification : notifications){
                SendMail sendMail = new SendMail(
                        sender,
                        notification.getEmail(),
                        notification.getObjet(),
                        "<div>" +
                                "<img src=\""+notification.getUrlImage()+"\"/>" + "<br/>"+
                                "<a href=\""+clientURL+"?email="+notification.getEmail()+"&eid="+ HashMail.getMd5(notification.getEmail())+"\">Se désabonner</a>"+
                                "</div>"
                        );
                sendMail.send();
                String newPath = moveFile(filePath, subDirectoryPath);
                sentRecordRepository.save(new SentRecord(newPath,LocalDateTime.now()));
            }
        } catch (Exception e) {
            String newPath = moveFile(filePath, errorDirectoryPath);
            errorRecordRepository.save(new ErrorRecord(newPath,e.getMessage()));
            LOGGER.error("error sending email ",e.getMessage());
            throw new ApiRequestException(e.getMessage());
        }
    }

    public String formatDate(FileTime fileTime){
        LocalDateTime localDateTime = fileTime
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();

        return localDateTime.format(DATE_FORMATTER);
    }

    public String moveFile(String filePath, String path) throws IOException {
        Path source = Paths.get(filePath);
        Path newDir = Paths.get(path);
        Files.createDirectories(newDir);
        String filePathString = Files.move(source, newDir.resolve(source.getFileName()),
                StandardCopyOption.REPLACE_EXISTING).toString();
        return filePathString;

    }

}
