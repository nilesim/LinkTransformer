package com.trendyol.linktransformer.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "path_lookup")
public class PathLookup {

  @Id
  private String pathKey;
  private String pathValue;

  public PathLookup() {
  }

  public PathLookup(String pathKey, String pathValue) {
    this.pathKey = pathKey;
    this.pathValue = pathValue;
  }

  @Column(name = "path_key", nullable = false)
  public String getPathKey() {
    return pathKey;
  }
  public void setPathKey(String pathKey) {
    this.pathKey = pathKey;
  }

  @Column(name = "path_value", nullable = false)
  public String getPathValue() {
    return pathValue;
  }
  public void setPathValue(String pathValue) {
    this.pathValue = pathValue;
  }

}