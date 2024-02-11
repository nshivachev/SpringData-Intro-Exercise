package com.softuni.bookshopsystem.services;

import java.io.IOException;

public interface SeedService {
    void seedAuthors() throws IOException;

    void seedBooks() throws IOException;

    void seedCategory() throws IOException;

    default void seedDatabase() throws IOException {
        seedAuthors();
        seedCategory();
        seedBooks();
    }
}
