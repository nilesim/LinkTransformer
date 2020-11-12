package com.selin.linktransformer.repository;

import com.selin.linktransformer.model.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Long> {
  Conversion findConversionByDeepKey(String deepKey);
  Conversion findConversionByWebKey(String webKey);

}
