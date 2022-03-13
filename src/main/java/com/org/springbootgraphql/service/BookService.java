package com.org.springbootgraphql.service;

import com.org.springbootgraphql.entity.Book;

public interface BookService {

    Book findBookById(String bookId);
}
