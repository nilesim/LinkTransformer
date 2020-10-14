package com.trendyol.linktransformer.repository;

import com.trendyol.linktransformer.model.EndpointLog;
import com.trendyol.linktransformer.model.PathLookup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EndpointLogRepository extends JpaRepository<EndpointLog, Long> {

}

