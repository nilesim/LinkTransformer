package com.selin.linktransformer.repository;

import com.selin.linktransformer.model.EndpointLog;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTraceRepository implements HttpTraceRepository {

  AtomicReference<HttpTrace> lastTrace = new AtomicReference<>();

  @Autowired
  EndpointLogRepository endpointLogRepository;

  @Override
  public List<HttpTrace> findAll() {
    return Collections.singletonList(lastTrace.get());
  }

  @Override
  public void add(HttpTrace trace) {
    if ("POST".equals(trace.getRequest().getMethod())) {
      lastTrace.set(trace);
      EndpointLog endpointLog = new EndpointLog(trace.getRequest().toString(), trace.getResponse().toString());
      endpointLogRepository.save(endpointLog);
    }
  }

}