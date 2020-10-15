package com.trendyol.linktransformer.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import com.trendyol.linktransformer.model.Link;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class LinkServiceTest {

  @Autowired
  LinkService linkService;

  @Test
  void whenWebProductDetailPageWithAllQueryParams() {
    assertThat(testWebEndpoint(
        "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064",
        "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
    ));
  }

  @Test
  void whenWebProductDetailPageWithNoQueryParams() {
    assertThat(testWebEndpoint(
        "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865",
        "ty://?Page=Product&ContentId=1925865"
    ));
  }

  @Test
  void whenWebProductDetailPageWithBoutiqueId() {
    assertThat(testWebEndpoint(
        "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?boutiqueId=439892",
        "ty://?Page=Product&ContentId=1925865&CampaignId=439892"
    ));
  }

  @Test
  void whenWebProductDetailPageWithMerchantId() {
    assertThat(testWebEndpoint(
        "https://www.trendyol.com/casio/erkek-kol-saati-p-1925865?merchantId=105064",
        "ty://?Page=Product&ContentId=1925865&MerchantId=105064"
    ));
  }

  @Test
  void whenWebSearchPageComes() {
    assertThat(testWebEndpoint(
        "https://www.trendyol.com/tum--urunler?q=elbise",
        "ty://?Page=Search&Query=elbise"
    ));
    assertThat(testWebEndpoint(
        "https://www.trendyol.com/tum--urunler?q=%C3%BCt%C3%BC",
        "ty://?Page=Search&Query=%C3%BCt%C3%BC"
    ));
  }

  @Test
  void whenWebOtherPages() {
    assertThat(testWebEndpoint(
        "https://www.trendyol.com/Hesabim/Favoriler",
        "ty://?Page=Home"
    ));
    assertThat(testWebEndpoint(
        "https://www.trendyol.com/Hesabim/#/Siparislerim",
        "ty://?Page=Home"
    ));
  }


  @Test
  void whenDeepProductDetailPageFullVars() throws MalformedURLException, UnsupportedEncodingException {
    String webLink = "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064";
    String deepLink = "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064";
    assertThat(testDeepEndpoint(webLink,deepLink));
  }

  @Test
  void whenDeepProductDetailPageNoVars() throws MalformedURLException, UnsupportedEncodingException {
    String webLink = "https://www.trendyol.com/brand/name-p-1925865";
    String deepLink = "ty://?Page=Product&ContentId=1925865";
    assertThat(testDeepEndpoint(webLink,deepLink));
  }

  @Test
  void whenDeepProductDetailPageOneVar() throws MalformedURLException, UnsupportedEncodingException {
    String deepLink = "ty://?Page=Product&ContentId=1925865&CampaignId=439892";
    String webLink = "https://www.trendyol.com/brand/name-p-1925865?boutiqueId=439892";
    assertThat(testDeepEndpoint(webLink,deepLink));
  }

  @Test
  void whenDeepSearchPageComes() {
    assertThat(testDeepEndpoint(
        "https://www.trendyol.com/tum--urunler?q=elbise",
        "ty://?Page=Search&Query=elbise"
    ));
    assertThat(testDeepEndpoint(
        "https://www.trendyol.com/tum--urunler?q=%C3%BCt%C3%BC",
        "ty://?Page=Search&Query=%C3%BCt%C3%BC"
    ));
  }

  @Test
  void whenDeepOtherPages() {
    assertThat(testDeepEndpoint(
        "https://www.trendyol.com",
        "ty://?Page=Favorites"
    ));
    assertThat(testDeepEndpoint(
        "https://www.trendyol.com",
        "ty://?Page=Orders"
    ));
  }

  Boolean testWebEndpoint(String webLink, String deepLink) {

    Link link = new Link( webLink,"");
    Link result = new Link("","");
    try {
      result = linkService.transformWebToDeepLink(link);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return deepLink.equals(result.getDeepLink());
  }

  Boolean testDeepEndpoint(String webLink, String deepLink) {
    Link link = new Link( "", deepLink);
    Link result = new Link("","");
    try {
      result = linkService.transformDeepToWebLink(link);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return webLink.equals(result.getHref());
  }

}