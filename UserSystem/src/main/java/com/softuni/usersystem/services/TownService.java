package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Town;

import java.util.Optional;

public interface TownService {
    void registerTown(String name, String country);

    Optional<Town> findByName(String name);
}
