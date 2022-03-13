package com.org.springbootgraphql.service.impl;

import com.org.springbootgraphql.entity.Author;
import com.org.springbootgraphql.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HY
 */
@Service
public class AuthorServiceImpl implements AuthorService {


    public static List<Author> AuthorList = new ArrayList<>() {{
        add(Author.builder().id("author-1").firstName("First Name 1").lastName("L1").build());
        add(Author.builder().id("author-2").firstName("First Name 2").lastName("L2").build());
        add(Author.builder().id("author-3").firstName("First Name 3").lastName("L3").build());
    }};


    @Override
    public Author findAuthorById(String authorId) {
        return AuthorList
                .stream()
                .filter(author -> author.getId().equals(authorId))
                .findFirst()
                .orElse(null);
    }
}
