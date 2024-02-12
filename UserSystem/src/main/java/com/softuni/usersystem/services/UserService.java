package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Album;
import com.softuni.usersystem.models.Town;

import java.util.List;

public interface UserService {
    void registerUser(String username, String password, String email, int age, Town bornTown, Town currentTown, List<Album> albums);
}
