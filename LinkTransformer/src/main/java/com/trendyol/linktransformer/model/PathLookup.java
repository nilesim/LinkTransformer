package com.trendyol.linktransformer.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "path_lookup")
public class PathLookup {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @Column(name = "path_key")
  private String pathKey;
  @Column(name = "path_value")
  private String pathValue;

  public PathLookup() {
  }

  public PathLookup(String pathKey, String pathValue) {
    this.pathKey = pathKey;
    this.pathValue = pathValue;
  }

  public long getId() {
    return id;
  }
  public void setId(long id) {
    this.id = id;
  }
  public String getPathKey() {
    return pathKey;
  }
  public void setPathKey(String pathKey) {
    this.pathKey = pathKey;
  }

  public String getPathValue() {
    return pathValue;
  }
  public void setPathValue(String pathValue) {
    this.pathValue = pathValue;
  }

}