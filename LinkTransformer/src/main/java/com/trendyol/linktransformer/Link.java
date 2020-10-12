package com.trendyol.linktransformer;

public class Link {

	private final long id;
	private final String href;

	public Link(long id, String href) {
		this.id = id;
		this.href = href;
	}

	public long getId() {
		return id;
	}

	public String getHref() {
		return href;
	}
}
