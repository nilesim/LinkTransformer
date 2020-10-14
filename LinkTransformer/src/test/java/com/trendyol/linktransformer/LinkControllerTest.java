package com.trendyol.linktransformer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(controllers = LinkController.class)
public class LinkControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private LinkController linkController;

  @SneakyThrows
  @Test
  void whenWebValidInput_thenReturns200() {
    Link link = new Link( "http://www.trendyol.com/casio/saat-p-1925865?boutiqueId=1&merchantId=2",
        "");
    mockMvc.perform(post("/web2deep")
        .content(objectMapper.writeValueAsString(link))
        .contentType("application/json"))
        .andExpect(status().isOk());
  }

  @SneakyThrows
  @Test
  void whenDeepValidInput_thenReturns200() throws Exception {
    Link link = new Link("",
        "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064");
    mockMvc.perform(post("/deep2web")
        .content(objectMapper.writeValueAsString(link))
        .contentType("application/json"))
        .andExpect(status().isOk());
  }

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
  void whenDeepProductDetailPageComes() {
    assertThat(testDeepEndpoint(
        "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064",
        "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
    ));

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
    MvcResult mvcResult = null;
    Link actualResponseBody = null;
    try {
      mvcResult = mockMvc.perform(post("/web2deep")
          .content(objectMapper.writeValueAsString(link))
          .contentType("application/json"))
          .andExpect(status().isOk())
          .andReturn();

      actualResponseBody = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Link.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return deepLink.equals(actualResponseBody.getDeepLink());
  }

  Boolean testDeepEndpoint(String webLink, String deepLink) {
    Link link = new Link( "", deepLink);
    MvcResult mvcResult = null;
    Link actualResponseBody = null;
    try {
      mvcResult = mockMvc.perform(post("/deep2web")
          .content(objectMapper.writeValueAsString(link))
          .contentType("application/json"))
          .andExpect(status().isOk())
          .andReturn();

      actualResponseBody = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Link.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return webLink.equals(actualResponseBody.getHref());
  }

}