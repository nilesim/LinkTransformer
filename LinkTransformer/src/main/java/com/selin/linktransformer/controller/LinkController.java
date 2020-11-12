package com.selin.linktransformer.controller;

import com.selin.linktransformer.model.Link;
import com.selin.linktransformer.service.LinkService;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LinkController {

	@Autowired
  LinkService linkService;

	@PostMapping("/web2deep")
  ResponseEntity<Link> web2deep(@RequestBody Link link)
			throws MalformedURLException, UnsupportedEncodingException {
		Link linked;
		linkService.validate(link);
		linked = linkService.transformWebToDeepLink(link);
    return new ResponseEntity<Link>(linked, HttpStatus.OK);
	}

	@PostMapping("/deep2web")
	ResponseEntity<Link> deep2web(@RequestBody Link link)
			throws MalformedURLException, UnsupportedEncodingException {
		Link linked;
		linkService.validate(link);
		linked = linkService.transformDeepToWebLink(link);
		return new ResponseEntity<Link>(linked, HttpStatus.OK);
	}

  @RequestMapping("/")
  public String home() {
    return "Linker Up and Running!";
  }

}
