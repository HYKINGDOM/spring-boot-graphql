package com.org.springbootgraphql.service.impl;

import com.org.springbootgraphql.entity.Book;
import com.org.springbootgraphql.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HY
 */
@Service
public class BookServiceImpl implements BookService {


    public final static List<Book> BookArray = new ArrayList<>() {{
        add(Book.builder().id("book-1").name("Harry Potter and the Philosopher's Stone").pageNumCount(100).authorId("author-1").build());
        add(Book.builder().id("book-2").name("Moby Dick").pageNumCount(200).authorId("author-2").build());
        add(Book.builder().id("book-3").name("Interview with the vampire").pageNumCount(400).authorId("author-3").build());
    }};

    @Override
    public Book findBookById(String bookId) {
        return BookArray
                .stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .orElse(null);
    }
}
