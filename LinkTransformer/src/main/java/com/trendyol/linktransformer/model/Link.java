package com.trendyol.linktransformer.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Link {

  private String href;
  private String deepLink;

	public Link(String href, String deepLink) {
		this.href = href;
		this.deepLink = deepLink;
	}

}
