package com.softuni.usersystem.repositories;

import com.softuni.usersystem.models.Album;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {
    Optional<Album> findByName(String name);
}
