package com.softuni.bookshopsystem.domain.models;

import com.softuni.bookshopsystem.domain.enums.AgeRestriction;
import com.softuni.bookshopsystem.domain.enums.EditionType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer copies;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "age_restriction", nullable = false)
    private AgeRestriction ageRestriction;

    @ManyToOne
    private Author author;

    @ManyToMany
    @JoinTable(name = "books_categories",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id")
    )
    private Set<Category> categories;
}
