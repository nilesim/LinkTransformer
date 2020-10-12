package com.trendyol.linktransformer;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {

	private static final String template = "Link: %s";
	private final AtomicLong counter = new AtomicLong();

	@PostMapping("/web2deep")
	Link web2deep(@RequestBody Link link) {
		return new Link(counter.incrementAndGet(), String.format(template, link.getHref()));
	}

	@PostMapping("/deep2web")
	Link deep2web(@RequestBody Link link) {
		return new Link(counter.incrementAndGet(), String.format(template, link.getHref()));
	}

}
