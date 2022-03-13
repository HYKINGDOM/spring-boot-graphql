package com.org.springbootgraphql.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author HY
 */
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Book {

    private String id;

    private String name;

    private Integer pageNumCount;

    private String authorId;

    private Author author;
}
