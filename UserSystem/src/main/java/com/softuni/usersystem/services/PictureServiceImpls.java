package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Picture;
import com.softuni.usersystem.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PictureServiceImpls implements PictureService {
    private final PictureRepository pictureRepository;

    @Autowired
    public PictureServiceImpls(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public void registerPicture(String title) {
        pictureRepository.save(Picture.builder().title(title).build());
    }

    @Override
    public Optional<Picture> findByTitle(String title) {
        return pictureRepository.findByTitle(title);
    }
}
