package com.selin.linktransformer.repository;

import com.selin.linktransformer.model.PathLookup;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PathLookupRepository extends JpaRepository<PathLookup, Long> {
  List<PathLookup> findPathLookupsByPathKey(String pathKey);
  PathLookup findByPathKey(String pathKey);
}

