package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Picture;

import java.util.Optional;

public interface PictureService {
    void registerPicture(String title);

    Optional<Picture> findByTitle(String title);
}
