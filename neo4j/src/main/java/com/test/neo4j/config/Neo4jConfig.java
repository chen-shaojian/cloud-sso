package com.test.neo4j.config;

import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "com.test.neo4j.services")
@EnableNeo4jRepositories(basePackages = { "com.test.neo4j.repositories" })
@EnableTransactionManagement
public class Neo4jConfig extends Neo4jConfiguration {

    @Override
    public SessionFactory getSessionFactory() {
        return new SessionFactory("com.test.neo4j.domain");
    }

}
