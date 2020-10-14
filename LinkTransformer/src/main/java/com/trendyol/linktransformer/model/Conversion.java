package com.trendyol.linktransformer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "web_deep_conversion")
public class Conversion {

  @Id
  private String webKey;
  private String deepKey;

  public Conversion() {
  }

  public Conversion(String webKey, String deepKey) {
    this.webKey = webKey;
    this.deepKey = deepKey;
  }

  @Column(name = "web_key", nullable = false)
  public String getWebKey() {
    return webKey;
  }
  public void setWebKey(String webKey) {
    this.webKey = webKey;
  }

  @Column(name = "deep_key", nullable = false)
  public String getDeepKey() {
    return deepKey;
  }
  public void setDeepKey(String deepKey) {
    this.deepKey = deepKey;
  }








}