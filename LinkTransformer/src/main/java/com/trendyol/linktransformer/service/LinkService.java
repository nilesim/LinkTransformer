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
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

@Component
@Slf4j
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

  public Link transformDeepToWebLink(Link link) throws MalformedURLException, UnsupportedEncodingException {
    String deepLink = pathLookupRepository.findByPathKey("baseDeepLink").getPathValue();
    String webLink = pathLookupRepository.findByPathKey("baseWebLink").getPathValue();

    String path = link.getDeepLink().replace(deepLink, "");

    List<PathLookup> validDeepPaths = pathLookupRepository.findPathLookupsByPathKey("validDeepPath");
    try {
      PathLookup matchedPath = validDeepPaths.stream()
          .filter(s -> new AntPathMatcher().match(s.getPathValue(), path))
          .findFirst().get(); // Product&ContentId={ContentId}

      Map<String, String> vars = new AntPathMatcher().extractUriTemplateVariables(
          matchedPath.getPathValue(),
          path);

      Conversion conv = conversionRepository.findConversionByDeepKey(matchedPath.getPathValue());
      String webPath = conv.getWebKey(); // /{brand}/{name}-p-{ContentId}
      webLink = webLink + webPath;
      String pathVariable = containsVariable(matchedPath.getPathValue());
      String pathValue = vars.get(pathVariable);
      String queries = "";
      if (!pathVariable.isEmpty() && pathValue.contains("=")) {
        String splitData[] = pathValue.split("\\&", 2);
        pathValue = splitData[0];
        queries = splitData[1];
      }
      if (!pathVariable.isEmpty()) {

        webLink = webLink.replace("{" + pathVariable + "}", pathValue);
        webLink = webLink.replace("{", "");
        webLink = webLink.replace("}", "");
      }
      if (!pathVariable.isEmpty() && !queries.isEmpty())
        webLink = webLink + "?" + convertQueryDeepParams(queries);
    } catch (NoSuchElementException e) {
      webLink = pathLookupRepository.findByPathKey("homeDeepLink").getPathValue();
    }
    link.setHref(webLink);
    return link;
  }

  private String convertQueryWebParams(String query) throws UnsupportedEncodingException {
    StringBuilder sb = new StringBuilder();
    String[] pairs = query.split("&");
    for (String pair : pairs) {
      if (sb.length() > 0)
        sb.append("&");
      int idx = pair.indexOf("=");
      String name = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
      String deepName = conversionRepository.findConversionByWebKey(name).getDeepKey();
      String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");

      sb.append(String.format("%s=%s",
          deepName,
          URLEncoder.encode(value, "UTF-8")
      ));
    }
    return sb.toString();
  }

  private String convertQueryDeepParams(String query) throws UnsupportedEncodingException {
    StringBuilder sb = new StringBuilder();
    String[] pairs = query.split("&");
    for (String pair : pairs) {
      if (sb.length() > 0)
        sb.append("&");
      int idx = pair.indexOf("=");
      String name = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
      String webName = conversionRepository.findConversionByDeepKey(name).getWebKey();
      String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");

      sb.append(String.format("%s=%s",
          webName,
          URLEncoder.encode(value, "UTF-8")
      ));
    }
    return sb.toString();
  }

  private String containsVariable(String path) {
    Pattern pattern = Pattern.compile("\\{(.*?)\\}");
    Matcher matcher = pattern.matcher(path);
    if(matcher.find() && matcher.groupCount()>0) {
      return matcher.group(1);
    }
    return "";
  }

  public Link transformWebToDeepLink(Link link) throws MalformedURLException {
    String deepLink = pathLookupRepository.findByPathKey("baseDeepLink").getPathValue();

    URL url = new URL(link.getHref());
    List<PathLookup> validURLPaths = pathLookupRepository.findPathLookupsByPathKey("validURLPath");
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

      if (!pathVariable.isEmpty() && url.getQuery() != null && !url.getQuery().isEmpty())
        deepLink = deepLink + "&" + convertQueryWebParams(url.getQuery());
      else if (url.getQuery() != null && !url.getQuery().isEmpty())
        deepLink = deepLink + convertQueryWebParams(url.getQuery());
    } catch (NoSuchElementException | UnsupportedEncodingException e) {
      deepLink = pathLookupRepository.findByPathKey("homeDeepLink").getPathValue();
    }
    link.setDeepLink(deepLink);
    return link;
  }
}
