package com.cdm.marketingbff.repository;

import com.cdm.marketingbff.model.SentRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SentRecordRepository extends JpaRepository<SentRecord,Long> {
}
