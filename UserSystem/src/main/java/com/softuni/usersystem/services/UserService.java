package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Album;
import com.softuni.usersystem.models.Town;
import com.softuni.usersystem.models.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    void registerUser(String username, String password, String email, int age, Town bornTown, Town currentTown, List<Album> albums, List<User> friends);

    Optional<User> findByUsername(String username);
}
