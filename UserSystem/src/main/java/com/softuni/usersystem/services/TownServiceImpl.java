package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Town;
import com.softuni.usersystem.repositories.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public void registerTown(String name, String country) {
        townRepository.save(Town.builder().name(name).country(country).build());
    }

    @Override
    public Optional<Town> findByName(String name) {
        return townRepository.findByName(name);
    }
}
