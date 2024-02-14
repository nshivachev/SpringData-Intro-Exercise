package com.softuni.usersystem.repositories;

import com.softuni.usersystem.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    List<User> findAllByLastTimeLoggedInBefore(LocalDateTime lastTimeLoggedIn);

    List<User> findAllByEmailEndingWith(String provider);
}
