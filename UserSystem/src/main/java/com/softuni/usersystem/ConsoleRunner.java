package com.softuni.usersystem;

import com.softuni.usersystem.models.*;
import com.softuni.usersystem.services.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private AlbumService albumService;
    private PictureService pictureService;
    private TownService townService;
    private UserService userService;
    private CountryService countryService;

    @Autowired
    public ConsoleRunner(AlbumService albumService, PictureService pictureService, TownService townService, UserService userService, CountryService countryService) {
        this.albumService = albumService;
        this.pictureService = pictureService;
        this.townService = townService;
        this.userService = userService;
        this.countryService = countryService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        pictureService.registerPicture("ProfilePic");
        List<Picture> pictures = List.of(pictureService.findByTitle("ProfilePic").orElseThrow(NoSuchElementException::new));
        albumService.registerAlbum("NikiAlbum", pictures);
        countryService.registerCountry("Bulgaria");
        Country bulgaria = countryService.findByName("Bulgaria").orElseThrow(NoSuchElementException::new);
        townService.registerTown("Yambol", bulgaria);
        townService.registerTown("Varna", bulgaria);
        Town yambol = townService.findByName("Yambol").orElseThrow(NoSuchElementException::new);
        Town varna = townService.findByName("Varna").orElseThrow(NoSuchElementException::new);
        List<Album> albums = List.of(albumService.findByName("NikiAlbum").orElseThrow(NoSuchElementException::new));
        userService.registerUser("Niki", "123", "niki@abv.bg", 33, yambol, varna, albums, null);
        List<User> friends = List.of(userService.findByUsername("Niki").orElseThrow(NoSuchElementException::new));
        userService.registerUser("Stefi", "123", "stefi@abv.bg", 3, varna, varna, albums, friends);
    }
}
