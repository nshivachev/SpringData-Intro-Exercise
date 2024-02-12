package com.softuni.usersystem;

import com.softuni.usersystem.models.Album;
import com.softuni.usersystem.models.Picture;
import com.softuni.usersystem.models.Town;
import com.softuni.usersystem.services.AlbumService;
import com.softuni.usersystem.services.PictureService;
import com.softuni.usersystem.services.TownService;
import com.softuni.usersystem.services.UserService;
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

    @Autowired
    public ConsoleRunner(AlbumService albumService, PictureService pictureService, TownService townService, UserService userService) {
        this.albumService = albumService;
        this.pictureService = pictureService;
        this.townService = townService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        pictureService.registerPicture("ProfilePic");
        List<Picture> pictures = List.of(pictureService.findByTitle("ProfilePic").orElseThrow(NoSuchElementException::new));
        albumService.registerAlbum("NikiAlbum", pictures);
        townService.registerTown("Yambol", "Bulgaria");
        townService.registerTown("Varna", "Bulgaria");
        Town bornTown = townService.findByName("Yambol").orElseThrow(NoSuchElementException::new);
        Town currentTown = townService.findByName("Varna").orElseThrow(NoSuchElementException::new);
        List<Album> albums = List.of(albumService.findByName("NikiAlbum").orElseThrow(NoSuchElementException::new));
        userService.registerUser("root", "123", "root@abv.bg", 18, bornTown, currentTown, albums);
    }
}
