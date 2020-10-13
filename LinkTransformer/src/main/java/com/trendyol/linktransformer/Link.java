package com.trendyol.linktransformer;

import lombok.Data;

@Data
public class Link {

	private final String href;
	private final String deepLink;

	public Link(String href, String deepLink) {
		this.href = href;
		this.deepLink = deepLink;
	}

}
