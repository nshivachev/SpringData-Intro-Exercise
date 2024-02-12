package com.softuni.usersystem.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(length = 30, nullable = false)
    private String username;

    @Column(length = 50, nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(name = "registered_on")
    private LocalDateTime registeredOn;

    @Column(name = "last_time_logged_in")
    private LocalDateTime lastTimeLoggedIn;

    @Basic
    private Integer age;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne
    @JoinColumn(name = "born_town_id", referencedColumnName = "id")
    private Town bornTown;

    @ManyToOne
    @JoinColumn(name = "current_town_id", referencedColumnName = "id")
    private Town currentTown;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(targetEntity = Album.class, mappedBy = "user",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Album> albums;
}
