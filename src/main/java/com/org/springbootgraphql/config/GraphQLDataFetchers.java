package com.org.springbootgraphql.config;

import com.org.springbootgraphql.entity.Author;
import com.org.springbootgraphql.entity.Book;
import com.org.springbootgraphql.entity.BookType;
import com.org.springbootgraphql.service.AuthorService;
import com.org.springbootgraphql.service.BookService;
import graphql.schema.DataFetcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GraphQLDataFetchers {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    public DataFetcher<Book> getBookByIdDataFetcher() {
        return dataFetchingEnvironment -> {
            String bookId = dataFetchingEnvironment.getArgument("id");
            return bookService.findBookById(bookId);
        };
    }

    public DataFetcher<Author> getAuthorDataFetcher() {
        return dataFetchingEnvironment -> {
            Book book = dataFetchingEnvironment.getSource();
            String authorId = book.getAuthorId();
            return authorService.findAuthorById(authorId);
        };
    }

    public DataFetcher<Author> getAllBookDataFetcher() {
        return dataFetchingEnvironment -> {
            String authorId = dataFetchingEnvironment.getArgument("id");
            return authorService.findAuthorById(authorId);
        };
    }

    public DataFetcher getAllEnum() {
        return dataFetchingEnvironment -> {
            String authorId = dataFetchingEnvironment.getArgument("type");
            return BookType.NOVEL;
        };
    }
}
