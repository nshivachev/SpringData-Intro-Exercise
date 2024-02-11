package com.softuni.bookshopsystem.services;

import com.softuni.bookshopsystem.domain.models.Author;
import com.softuni.bookshopsystem.repositories.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void seedAuthors(List<Author> authors) {
        authorRepository.saveAll(authors);
    }

    @Override
    public boolean isDataSeeded() {
        return authorRepository.count() > 0;
    }

    @Override
    public Author getRandomAuthor() {
        final long repositoryCount = authorRepository.count();

        if (authorRepository.count() == 0) throw new RuntimeException();

        return authorRepository.findAuthorById(new Random().nextLong(1L, repositoryCount + 1L))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Author> findDistinctByBooksBefore(LocalDate releaseDate) {
        return authorRepository.findDistinctByBooksReleaseDateBefore(releaseDate)
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<Author> findAllOrderByBooksSize() {
        return authorRepository.findAll()
                .stream()
                .sorted((a1, a2) -> a2.getBooks().size() - a1.getBooks().size())
                .collect(Collectors.toList());
    }
}
