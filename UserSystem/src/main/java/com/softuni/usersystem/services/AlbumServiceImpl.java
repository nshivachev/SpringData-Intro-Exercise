package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Album;
import com.softuni.usersystem.models.Picture;
import com.softuni.usersystem.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public void registerAlbum(String name, List<Picture> pictures) {
        albumRepository.save(Album.builder().name(name).pictures(pictures).build());
    }

    @Override
    public Optional<Album> findByName(String name) {
        return albumRepository.findByName(name);
    }
}
