package com.softuni.bookshopsystem.services;

import com.softuni.bookshopsystem.domain.models.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    void seedCategories(List<Category> categories);

    boolean isDataSeeded();

    Set<Category> getRandomCategories();
}
