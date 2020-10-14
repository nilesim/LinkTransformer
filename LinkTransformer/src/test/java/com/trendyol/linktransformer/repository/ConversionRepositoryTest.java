package com.trendyol.linktransformer.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.trendyol.linktransformer.model.Conversion;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class ConversionRepositoryTest {

  @Autowired
  ConversionRepository conversionRepository;

  @Test
  void convertTest() {
    String webKey = "boutiqueId";
    String deepKey = "CampaignId";
    Conversion conversion = conversionRepository.findConversionByWebKey(webKey);
    assertEquals(conversion.getDeepKey(), deepKey);
    conversion = conversionRepository.findConversionByDeepKey(deepKey);
    assertEquals(conversion.getWebKey(), webKey);
  }

}