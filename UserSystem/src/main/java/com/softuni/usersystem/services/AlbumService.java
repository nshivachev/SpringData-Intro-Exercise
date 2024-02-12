package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Album;
import com.softuni.usersystem.models.Picture;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    void registerAlbum(String name, List<Picture> pictures);

    Optional<Album> findByName(String name);
}
