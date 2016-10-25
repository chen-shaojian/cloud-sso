package com.test.neo4j.repositories;

import com.test.neo4j.domain.Unit;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends GraphRepository<Unit> {
    Unit findByName(String name);
}


