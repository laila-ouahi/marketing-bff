package com.cdm.marketingbff.repository;

import com.cdm.marketingbff.model.ErrorRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRecordRepository extends JpaRepository<ErrorRecord,Long> {
}
