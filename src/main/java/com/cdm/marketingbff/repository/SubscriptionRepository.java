package com.cdm.marketingbff.repository;

import com.cdm.marketingbff.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    Optional<Subscription> findByEmail(String email);
    List<Subscription> findByIsSubscribedFalse();
}
