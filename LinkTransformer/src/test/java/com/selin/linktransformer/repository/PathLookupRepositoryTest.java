package com.selin.linktransformer.repository;

import static org.junit.jupiter.api.Assertions.*;

import com.selin.linktransformer.model.PathLookup;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.NONE)
class PathLookupRepositoryTest {

  @Autowired
  PathLookupRepository pathLookupRepository;

  @Test
  void convertTest() {
    String pathKey = "baseDeepLink";
    String pathValue = "ty://?Page=";
    PathLookup pathLookup = pathLookupRepository.findByPathKey(pathKey);
    assertEquals(pathLookup.getPathValue(), pathValue);
  }
}