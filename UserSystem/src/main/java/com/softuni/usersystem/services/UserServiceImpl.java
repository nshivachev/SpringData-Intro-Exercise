package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Album;
import com.softuni.usersystem.models.Picture;
import com.softuni.usersystem.models.Town;
import com.softuni.usersystem.models.User;
import com.softuni.usersystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(String username, String password, String email, int age, Town bornTown, Town currentTown, List<Album> albums) {
        userRepository.save(User.builder()
                .username(username)
                .password(password)
                .email(email)
                .age(age)
                .bornTown(bornTown)
                .currentTown(currentTown)
                .albums(albums)
                .build());
    }
}
