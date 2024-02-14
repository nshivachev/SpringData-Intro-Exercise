package com.softuni.usersystem.services;

import com.softuni.usersystem.models.Album;
import com.softuni.usersystem.models.Town;
import com.softuni.usersystem.models.User;
import com.softuni.usersystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(String username, String password, String email, int age, Town bornTown, Town currentTown, List<Album> albums, List<User> friends) {
        if (findByUsername(username).isEmpty())
            userRepository.save(User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .age(age)
                    .bornTown(bornTown)
                    .currentTown(currentTown)
                    .albums(albums)
                    .friends(friends)
                    .build());
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deactivateInactiveUsers(LocalDateTime lastTimeLoggedIn) {
        userRepository
                .findAllByLastTimeLoggedInBefore(lastTimeLoggedIn)
                .forEach(user -> user.setDeleted(true));
    }

    @Override
    public List<User> getAllUsersByEmailProvider(String provider) {
        return userRepository.findAllByEmailEndingWith(provider);
    }
}
