package com.cdm.marketingbff.controller;

import com.cdm.marketingbff.dto.CheckSubscriptionDTO;
import com.cdm.marketingbff.dto.SubscriptionRequestDTO;
import com.cdm.marketingbff.dto.SubscriptionResponseDTO;
import com.cdm.marketingbff.model.Subscription;
import com.cdm.marketingbff.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/cdm/marketing/client")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/subscribe")
    ResponseEntity<SubscriptionResponseDTO> subscribe(@RequestBody SubscriptionRequestDTO subscriptionRequestDTO){
        Subscription subscription = new Subscription(
                subscriptionRequestDTO.getEmail(),
                subscriptionRequestDTO.getMotif(),
                LocalDateTime.now(),
                true
        );
        SubscriptionResponseDTO subscriptionResponseDTO = subscriptionService.editSubscription(subscription);
       return  ResponseEntity.ok(subscriptionResponseDTO);
    }

    @PostMapping("/unsubscribe")
    ResponseEntity<SubscriptionResponseDTO> unsubscribe(@RequestBody SubscriptionRequestDTO subscriptionRequestDTO){
        if(!subscriptionService.verifyHash(subscriptionRequestDTO.getEmail(),subscriptionRequestDTO.getHash())){
            SubscriptionResponseDTO subscriptionResponseDTO = new SubscriptionResponseDTO("2","Subscription denied");
            return ResponseEntity.ok(subscriptionResponseDTO);
        }
        Subscription subscription = new Subscription(
                subscriptionRequestDTO.getEmail(),
                subscriptionRequestDTO.getMotif(),
                LocalDateTime.now(),
                false
        );
        SubscriptionResponseDTO subscriptionResponseDTO = subscriptionService.editSubscription(subscription);
        return ResponseEntity.ok(subscriptionResponseDTO);
    }

    @GetMapping("/list")
    ResponseEntity<List<Subscription>> list(){
        List<Subscription> subscriptions = subscriptionService.listSubscription();
        return  ResponseEntity.ok(subscriptions);
    }

    @PostMapping("/subscription/check")
    ResponseEntity<Boolean> checkSubscription (@RequestBody CheckSubscriptionDTO checkSubscriptionDTO){
        Boolean check = subscriptionService.check(checkSubscriptionDTO.getEmail());
        return ResponseEntity.ok(check);
    }
}
