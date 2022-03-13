package com.org.springbootgraphql.service;

import com.org.springbootgraphql.entity.Author;

public interface AuthorService {

    Author findAuthorById(String bookId);
}
