package com.softuni.bookshopsystem.services;

import com.softuni.bookshopsystem.domain.models.Category;
import com.softuni.bookshopsystem.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedCategories(List<Category> categories) {
        categoryRepository.saveAll(categories);
    }

    @Override
    public boolean isDataSeeded() {
        return categoryRepository.count() > 0;
    }

    @Override
    public Set<Category> getRandomCategories() {
        final int repositoryCount = (int) categoryRepository.count();

        if (this.categoryRepository.count() == 0) throw new RuntimeException();

        final int randomNum = new Random().nextInt(1, repositoryCount + 1);

        final Set<Integer> categoriesIds = new HashSet<>();

        for (int i = 1; i <= randomNum; i++) {
            final int randomId = new Random().nextInt(1, repositoryCount + 1);
            categoriesIds.add(randomId);
        }

        return new HashSet<>(this.categoryRepository.findAllById(categoriesIds));
    }
}
