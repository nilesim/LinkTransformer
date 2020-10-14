package com.trendyol.linktransformer.service;

import com.trendyol.linktransformer.Link;
import com.trendyol.linktransformer.model.Conversion;
import com.trendyol.linktransformer.model.PathLookup;
import com.trendyol.linktransformer.repository.ConversionRepository;
import com.trendyol.linktransformer.repository.PathLookupRepository;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

@Component
public class LinkService {

  @Autowired
  private ConversionRepository conversionRepository;

  @Autowired
  private PathLookupRepository pathLookupRepository;

  public boolean validate(Link link) {
    if (!link.getHref().isEmpty()) {
      PathLookup lookup = pathLookupRepository.findByPathKey("baseWebLink");
      return link.getHref().contains(lookup.getPathValue());
    } else if (!link.getDeepLink().isEmpty()) {
      PathLookup lookup = pathLookupRepository.findByPathKey("baseDeepLink");
      return link.getDeepLink().contains(lookup.getPathValue());
    }
    return false;
  }

  public Link transformWebToDeepLink(Link link) throws MalformedURLException, UnsupportedEncodingException {
    String deepLink = pathLookupRepository.findByPathKey("baseDeepLink").getPathValue();

    URL url = new URL(link.getHref());
    List<PathLookup> validURLPaths = pathLookupRepository.findAllByPathKey("validURLPath");
    try {
      PathLookup matchedPath = validURLPaths.stream()
          .filter(s -> new AntPathMatcher().match(s.getPathValue(), url.getPath()))
          .findFirst().get();

      Map<String, String> vars = new AntPathMatcher().extractUriTemplateVariables(
          matchedPath.getPathValue(),
          url.getPath());


      Conversion conv = conversionRepository.findConversionByWebKey(matchedPath.getPathValue());
      String deepPath = conv.getDeepKey();
      deepLink = deepLink + deepPath;
      String pathVariable = containsVariable(deepPath);

      if (!pathVariable.isEmpty())
        deepLink = deepLink.replace("{" + pathVariable + "}", vars.get(pathVariable));

      if (url.getQuery() != null && !url.getQuery().isEmpty())
        deepLink = deepLink + convertQueryParams(url.getQuery());
    } catch (NoSuchElementException e) {
      deepLink = pathLookupRepository.findByPathKey("homeDeepLink").getPathValue();
    }
    link.setDeepLink(deepLink);
    return link;
  }

  private String convertQueryParams(String query) throws UnsupportedEncodingException {
    StringBuilder sb = new StringBuilder();
    String[] pairs = query.split("&");
    for (String pair : pairs) {
      sb.append("&");
      int idx = pair.indexOf("=");
      String name = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
      String deepName = conversionRepository.findConversionByWebKey(name).getDeepKey();
      String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");

      sb.append(String.format("%s=%s",
          URLEncoder.encode(deepName, "UTF-8"),
          URLEncoder.encode(value, "UTF-8")
      ));
    }
    return sb.toString();
  }

  private String containsVariable(String deepPath) {
    Pattern pattern = Pattern.compile("\\{(.*?)\\}");
    Matcher matcher = pattern.matcher(deepPath);
    if(matcher.find() && matcher.groupCount()>0) {
      return matcher.group(1);
    }
    return "";
  }

}
