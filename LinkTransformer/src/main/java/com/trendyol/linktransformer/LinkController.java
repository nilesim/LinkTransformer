package com.trendyol.linktransformer;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {

	private static final String template = "Link: %s";

	@PostMapping("/web2deep")
	Link web2deep(@RequestBody Link link) {
		return new Link(String.format(template, link.getHref()), link.getDeepLink());
	}

	@PostMapping("/deep2web")
	Link deep2web(@RequestBody Link link) {
		return new Link(String.format(template, link.getHref()), link.getDeepLink());
	}
  @RequestMapping("/")
  public String home() {
    return "Linker Up and Running!";
  }

}
