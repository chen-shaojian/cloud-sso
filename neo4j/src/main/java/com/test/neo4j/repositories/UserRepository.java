package com.test.neo4j.repositories;

import com.test.neo4j.domain.User;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Map;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserRepository extends GraphRepository<User> {
    User findByName(@Param("name") String name);

    List<User> findByNameLike(@Param("name") String name);

    @Query("MATCH (u:User) WHERE u.name =~ ('(?i).*'+{name}+'.*') RETURN u")
    List<User> findByNameContaining(@Param("name") String name);

    @Query("MATCH (n:Unit)<-[:BELONG]-(u:User) WHERE ID(n) ={id} RETURN u")
    Iterable<User> getUsersByUnitId(@Param("id") Long id);

}


