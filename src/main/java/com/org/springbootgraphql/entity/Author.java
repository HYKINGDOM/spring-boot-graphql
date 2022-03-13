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
public class Author {

    private String id;

    private String firstName;

    private String lastName;
}
