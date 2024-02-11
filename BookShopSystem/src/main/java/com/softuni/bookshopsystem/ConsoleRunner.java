package com.softuni.bookshopsystem;

import com.softuni.bookshopsystem.services.AuthorService;
import com.softuni.bookshopsystem.services.BookService;
import com.softuni.bookshopsystem.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ConsoleRunner implements CommandLineRunner {
    public static final LocalDate BOOK_YEAR_AFTER = LocalDate.of(2000, 1, 1);
    public static final LocalDate BOOK_YEAR_BEFORE = LocalDate.of(1990, 1, 1);
    public static final String AUTHOR_FIRST_NAME = "George";
    public static final String AUTHOR_LAST_NAME = "Powell";

    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public ConsoleRunner(SeedService seedService, BookService bookService, AuthorService authorService) {
        this.seedService = seedService;
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedDatabase();

        getAllBooksAfterAGivenYear();
        getAllAuthorsWithBooksReleaseDateBefore();
        getAllAuthorsSortByBooksCount();
        getAllBooksByAuthorName();
    }

    private void getAllBooksAfterAGivenYear() {
        this.bookService
                .findAllByReleaseDateAfter(BOOK_YEAR_AFTER)
                .forEach(book -> System.out.println(book.getTitle()));
    }

    private void getAllAuthorsWithBooksReleaseDateBefore() {
        this.authorService
                .findDistinctByBooksBefore(BOOK_YEAR_BEFORE)
                .forEach(author -> System.out.printf("%s %s%n",
                        author.getFirstName(), author.getLastName()));
    }

    private void getAllAuthorsSortByBooksCount() {
        this.authorService
                .findAllOrderByBooksSize()
                .forEach(author -> System.out.printf("%s %s -> %d%n",
                        author.getFirstName(), author.getLastName(), author.getBooks().size()));
    }

    private void getAllBooksByAuthorName() {
        this.bookService
                .findAllByAuthorName(AUTHOR_FIRST_NAME, AUTHOR_LAST_NAME)
                .forEach(book -> System.out.printf("%s -> %s -> %d%n",
                        book.getTitle(), book.getReleaseDate(), book.getCopies()));
    }
}
