package com.test.neo4j.repositories;

import com.test.neo4j.domain.Role;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GraphRepository<Role> {
    Role findByName(String name);
}


