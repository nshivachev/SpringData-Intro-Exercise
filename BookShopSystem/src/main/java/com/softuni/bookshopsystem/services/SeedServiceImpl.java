package com.softuni.bookshopsystem.services;

import com.softuni.bookshopsystem.domain.enums.AgeRestriction;
import com.softuni.bookshopsystem.domain.enums.EditionType;
import com.softuni.bookshopsystem.domain.models.Author;
import com.softuni.bookshopsystem.domain.models.Book;
import com.softuni.bookshopsystem.domain.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.softuni.bookshopsystem.constants.FilePath.*;

@Component
public class SeedServiceImpl implements SeedService {
    private final AuthorService authorService;
    private final BookService bookService;
    private final CategoryService categoryService;

    @Autowired
    public SeedServiceImpl(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedAuthors() throws IOException {
        if (authorService.isDataSeeded()) return;

        authorService
                .seedAuthors(Files.readAllLines(Path.of(RESOURCE_PATH + AUTHORS_FILE_NAME))
                        .stream()
                        .filter(row -> !row.isBlank())
                        .map(row -> Author.builder()
                                .firstName(row.split("\\s+")[0])
                                .lastName(row.split("\\s+")[1])
                                .build())
                        .collect(Collectors.toList()));
    }

    @Override
    public void seedBooks() throws IOException {
        bookService
                .seedBooks(Files.readAllLines(Path.of(RESOURCE_PATH + BOOKS_FILE_NAME))
                        .stream()
                        .filter(row -> !row.isBlank())
                        .map(row -> {
                            String[] data = row.split("\\s+");

                            return Book.builder()
                                    .title(Arrays.stream(data).skip(5).collect(Collectors.joining(" ")))
                                    .editionType(EditionType.values()[Integer.parseInt(data[0])])
                                    .price(new BigDecimal(data[3]))
                                    .releaseDate(LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy")))
                                    .ageRestriction(AgeRestriction.values()[Integer.parseInt(data[4])])
                                    .author(authorService.getRandomAuthor())
                                    .categories(categoryService.getRandomCategories())
                                    .copies(Integer.parseInt(data[2]))
                                    .build();
                        })
                        .collect(Collectors.toList()));
    }

    @Override
    public void seedCategory() throws IOException {
        if (categoryService.isDataSeeded()) return;

        categoryService
                .seedCategories((Files.readAllLines(Path.of(RESOURCE_PATH + CATEGORIES_FILE_NAME)))
                        .stream()
                        .filter(row -> !row.isBlank())
                        .map(row -> Category.builder()
                                .name(row)
                                .build())
                        .collect(Collectors.toList()));
    }
}
