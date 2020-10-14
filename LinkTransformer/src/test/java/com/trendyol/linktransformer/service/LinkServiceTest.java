package com.trendyol.linktransformer.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.trendyol.linktransformer.Link;
import com.trendyol.linktransformer.repository.ConversionRepository;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class LinkServiceTest {

  @Autowired
  LinkService linkService;

  @Test
  void whenWebProductDetailPageWithAllQueryParams()
      throws MalformedURLException, UnsupportedEncodingException {
    String webLink = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064";
    String deepLink = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";
    Link link = new Link(webLink, "");
    Link result = linkService.transformWebToDeepLink(link);
    assertEquals(result.getDeepLink(), deepLink);
  }

  @Test
  void whenWebProductDetailPageWithNoQueryParams()
      throws MalformedURLException, UnsupportedEncodingException {
    String webLink = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865";
    String deepLink = "ty://?Page=Product&ContentId=1925865";
    Link link = new Link(webLink, "");
    Link result = linkService.transformWebToDeepLink(link);
    assertEquals(result.getDeepLink(), deepLink);
  }

  @Test
  void whenWebProductDetailPageWithBoutiqueId()
      throws MalformedURLException, UnsupportedEncodingException {
    String webLink = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892";
    String deepLink = "ty://?Page=Product&ContentId=1925865&CampaignId=439892";
    Link link = new Link(webLink, "");
    Link result = linkService.transformWebToDeepLink(link);
    assertEquals(result.getDeepLink(), deepLink);
  }

  @Test
  void whenWebProductDetailPageWithMerchantId()
      throws MalformedURLException, UnsupportedEncodingException {
    String webLink = "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?merchantId=105064";
    String deepLink = "ty://?Page=Product&ContentId=1925865&MerchantId=105064";
    Link link = new Link(webLink, "");
    Link result = linkService.transformWebToDeepLink(link);
    assertEquals(result.getDeepLink(), deepLink);
  }

  @Test
  void whenWebSearchPageComes() throws MalformedURLException, UnsupportedEncodingException {
    String webLink = "https://www.trendyol.com/tum--urunler?q=elbise";
    String deepLink = "ty://?Page=Search&Query=elbise";
    Link link = new Link(webLink, "");
    Link result = linkService.transformWebToDeepLink(link);
    assertEquals(result.getDeepLink(), deepLink);
    webLink = "https://www.trendyol.com/tum--urunler?q=%C3%BCt%C3%BC";
    deepLink = "ty://?Page=Search&Query=%C3%BCt%C3%BC";
    link = new Link(webLink, "");
    result = linkService.transformWebToDeepLink(link);
    assertEquals(result.getDeepLink(), deepLink);
  }

  @Test
  void whenWebOtherPages() throws MalformedURLException, UnsupportedEncodingException {
    String webLink = "https://www.trendyol.com/Hesabim/Favoriler";
    String deepLink = "ty://?Page=Home";
    Link link = new Link(webLink, "");
    Link result = linkService.transformWebToDeepLink(link);
    assertEquals(result.getDeepLink(), deepLink);
    webLink = "https://www.trendyol.com/Hesabim/#/Siparislerim";
    deepLink = "ty://?Page=Home";
    link = new Link(webLink, "");
    result = linkService.transformWebToDeepLink(link);
    assertEquals(result.getDeepLink(), deepLink);
  }

}