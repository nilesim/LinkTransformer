package com.selin.linktransformer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "endpoint_logs")
public class EndpointLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "request")
    private String request;
    @Column(name = "response")
    private String response;

  public EndpointLog(String request, String response) {
    this.request = request;
    this.response = response;
  }
}
