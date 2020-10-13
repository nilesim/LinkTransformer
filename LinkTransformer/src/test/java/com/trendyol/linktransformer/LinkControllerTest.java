package com.trendyol.linktransformer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = LinkController.class)
public class LinkControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  void whenValidInput_thenReturns200() throws Exception {

    Link link = new Link( "http://www.trendyol.com/casio/saat-p-1925865?boutiqueId=1&merchantId=2",
        "deepLink");

    mockMvc.perform(post("/web2deep")
        .content(objectMapper.writeValueAsString(link))
        .contentType("application/json"))
        .andExpect(status().isOk());
  }

}