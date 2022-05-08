package com.cdm.marketingbff.service;

import com.cdm.marketingbff.dto.SubscriptionResponseDTO;
import com.cdm.marketingbff.model.Subscription;

import java.util.List;

public interface SubscriptionService {
    public SubscriptionResponseDTO editSubscription(Subscription subscription);
    public List<Subscription> listSubscription();
    public boolean check(String mail);
    public boolean verifyHash(String mail,String hash);
}
