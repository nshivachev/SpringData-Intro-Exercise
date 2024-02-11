package com.softuni.bookshopsystem.services;

import com.softuni.bookshopsystem.domain.models.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    void seedAuthors(List<Author> authors);

    boolean isDataSeeded();

    Author getRandomAuthor();

    List<Author>findDistinctByBooksBefore(LocalDate releaseDate);

    List<Author>findAllOrderByBooksSize();
}
