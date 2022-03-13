package com.org.springbootgraphql.config;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import com.org.springbootgraphql.service.BookService;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

/**
 * schema.graphqls
 *
 * @author HY
 */
@Component
public class SchemaGraphQLProvider {


    @Autowired
    private GraphQLDataFetchers graphQLDataFetchers;

    private GraphQL graphQL;

    private static Map<String, Object> objectObjectHashMap = new HashMap<>();

    static {
        objectObjectHashMap.put("NOVEL", "NOVEL");
        objectObjectHashMap.put("BIOGRAPHY", "BIOGRAPHY");
        objectObjectHashMap.put("TECHNOLOGY", "TECHNOLOGY");
        objectObjectHashMap.put("DOCUMENTARY", "DOCUMENTARY");

    }

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("schema.graphqls");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("bookById", graphQLDataFetchers.getBookByIdDataFetcher()))
                .type(newTypeWiring("Book")
                        .dataFetcher("author", graphQLDataFetchers.getAuthorDataFetcher()))
                .type(newTypeWiring("Query")
                        .dataFetcher("author", graphQLDataFetchers.getAllBookDataFetcher()))
                .type(newTypeWiring("Query")
                        .dataFetcher("bookType", graphQLDataFetchers.getAllEnum()))
                .build();
    }

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

}
