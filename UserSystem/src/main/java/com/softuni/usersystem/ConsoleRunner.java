package com.softuni.usersystem;

import com.softuni.usersystem.models.*;
import com.softuni.usersystem.services.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
        albumService.registerAlbum("NikiAlbum", getPicturesByTitles("ProfilePic"));
        albumService.registerAlbum("StefiAlbum", getPicturesByTitles("ProfilePic"));
        countryService.registerCountry("Bulgaria");
        townService.registerTown("Yambol", getCountryByName("Bulgaria"));
        townService.registerTown("Varna", getCountryByName("Bulgaria"));
        userService.registerUser("Niki", "123", "niki@abv.bg", 33, getTownByName("Yambol"), getTownByName("Varna"), getAlbumsByNames("NikiAlbum"), null);
        userService.registerUser("Stefi", "123", "stefi@abv.bg", 3, getTownByName("Varna"), getTownByName("Yambol"), getAlbumsByNames("StefiAlbum"), getUsersByUsernames("Niki"));

        List<User> users = userService.getAllUsersByEmailProvider("abv.bg");

        if (users.isEmpty()) {
            System.out.println("No users found with email domain abv.bg");
            return;
        }

        users.forEach(user -> System.out.println(user.getEmail()));
        users.forEach(user -> user.setLastTimeLoggedIn(LocalDateTime.now()));
        users.forEach(user -> userService.deactivateInactiveUsers(LocalDateTime.now()));
    }

    private List<Picture> getPicturesByTitles(String... titles) {
        return Arrays.stream(titles)
                .map(title -> pictureService.findByTitle(title).orElseThrow(NoSuchElementException::new))
                .collect(Collectors.toList());
    }

    private List<Album> getAlbumsByNames(String... names) {
        return Arrays.stream(names)
                .map(name -> albumService.findByName(name).orElseThrow(NoSuchElementException::new))
                .collect(Collectors.toList());
    }

    private Country getCountryByName(String name) {
        return countryService.findByName(name).orElseThrow(NoSuchElementException::new);
    }

    private Town getTownByName(String name) {
        return townService.findByName(name).orElseThrow(NoSuchElementException::new);
    }

    private List<User> getUsersByUsernames(String... usernames) {
        return Arrays.stream(usernames)
                .map(username -> userService.findByUsername(username).orElseThrow(NoSuchElementException::new))
                .collect(Collectors.toList());
    }
}
