package com.softuni.usersystem.repositories;

import com.softuni.usersystem.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {
    Optional<Picture> findByTitle(String title);
}
