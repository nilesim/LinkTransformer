package com.selin.linktransformer.repository;

import com.selin.linktransformer.model.EndpointLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointLogRepository extends JpaRepository<EndpointLog, Long> {

}

