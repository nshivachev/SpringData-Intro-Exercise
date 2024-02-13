package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Country;
import com.softuni.usersystem.models.Town;

import java.util.Optional;

public interface TownService {
    void registerTown(String name, Country country);

    Optional<Town> findByName(String name);
}
