package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Country;

import java.util.Optional;

public interface CountryService {
    void registerCountry(String name);

    Optional<Country> findByName(String name);
}
