package com.softuni.bookshopsystem.repositories;

import com.softuni.bookshopsystem.domain.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}