package com.softuni.bookshopsystem.services;

import com.softuni.bookshopsystem.domain.models.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks(List<Book> books);

    List<Book> findAllByReleaseDateAfter(LocalDate localDate);

    List<Book> findAllByAuthorName(String firstName, String lastName);
}
