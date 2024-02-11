package com.softuni.bookshopsystem.services;

import com.softuni.bookshopsystem.domain.models.Book;
import com.softuni.bookshopsystem.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void seedBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    @Override
    public List<Book> findAllByReleaseDateAfter(LocalDate localDate) {
        return bookRepository.findAllByReleaseDateAfter(localDate).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Book> findAllByAuthorName(String firstName, String lastName) {
        return bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName)
                .orElseThrow(NoSuchElementException::new);
    }
}
