package com.trendyol.linktransformer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.UnsupportedEncodingException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@WebMvcTest(controllers = LinkController.class)
public class LinkControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

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
        "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064",
        "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
    ));
  }

  @Test
  void whenWebProductDetailPageWithBoutiqueId() {
    assertThat(testWebEndpoint(
        "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064",
        "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
    ));
  }

  @Test
  void whenWebProductDetailPageWithMerchantId() {
    assertThat(testWebEndpoint(
        "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064",
        "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
    ));
  }

  @Test
  void whenWebSearchPageComes() {
    assertThat(testWebEndpoint(
        "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064",
        "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
    ));
  }

  @Test
  void whenWebOtherPages() {
    assertThat(testWebEndpoint(
        "https://www.trendyol.com/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064",
        "ty://?Page=Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"
    ));
  }

  @Test
  void whenDeepProductDetailPageComes() {

  }

  @Test
  void whenDeepSearchPageComes() {

  }

  @Test
  void whenDeepOtherPages() {

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
    } catch (UnsupportedEncodingException e) {
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
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return webLink.equals(actualResponseBody.getHref());
  }

}