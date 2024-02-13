package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Country;
import com.softuni.usersystem.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void registerCountry(String name) {
        countryRepository.save(Country.builder().name(name).build());

    }

    @Override
    public Optional<Country> findByName(String name) {
        return countryRepository.findByName(name);
    }
}
