package com.cdm.marketingbff.serviceImpl;

import com.cdm.marketingbff.dto.SubscriptionResponseDTO;
import com.cdm.marketingbff.model.Subscription;
import com.cdm.marketingbff.repository.SubscriptionRepository;
import com.cdm.marketingbff.service.SubscriptionService;
import com.cdm.marketingbff.utils.HashMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    SubscriptionRepository subscriptionRepository;

    @Override
    public SubscriptionResponseDTO editSubscription(Subscription subscription) {
        if(subscriptionRepository.findByEmail(subscription.getEmail()).isPresent()){
            Subscription subscriptionFound = subscriptionRepository.findByEmail(subscription.getEmail()).get();
            subscription.setId(subscriptionFound.getId());
            subscriptionRepository.save(subscription);
            return new SubscriptionResponseDTO("1","Subscription edited");
        }
        subscriptionRepository.save(subscription);
        return new SubscriptionResponseDTO("1","Subscription edited");
    }

    @Override
    public List<Subscription> listSubscription() {
        return subscriptionRepository.findAll();
    }

    @Override
    public boolean check(String email) {
        return subscriptionRepository.findByEmail(email).get().isSubscribed();
    }

    @Override
    public boolean verifyHash(String mail, String hash) {
        return hash.equals(HashMail.getMd5(mail));
    }
}
