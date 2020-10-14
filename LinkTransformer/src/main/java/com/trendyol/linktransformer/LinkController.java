package com.trendyol.linktransformer;

import com.trendyol.linktransformer.service.LinkService;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {

	@Autowired
	LinkService linkService;

	@PostMapping("/web2deep")
	Link web2deep(@RequestBody Link link) {
		Link linked;
		linkService.validate(link);
		try {
			linked = linkService.transformWebToDeepLink(link);
			System.out.println("Deep link: " + linked.getDeepLink());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new Link(link.getHref(), link.getDeepLink());
	}

	@PostMapping("/deep2web")
	Link deep2web(@RequestBody Link link) {
		return new Link(String.format(link.getHref()), link.getDeepLink());
	}
  @RequestMapping("/")
  public String home() {
    return "Linker Up and Running!";
  }

}
