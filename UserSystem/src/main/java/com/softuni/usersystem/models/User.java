package com.softuni.usersystem.models;

import com.softuni.usersystem.annotations.email.Email;
import com.softuni.usersystem.annotations.password.Password;
import com.softuni.usersystem.constants.Constants;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    @Password
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;

    @Column(name = "registered_on")
    private LocalDateTime registeredOn;

    @Column(name = "last_time_logged_in")
    private LocalDateTime lastTimeLoggedIn;

    @Min(value = 1, message = Constants.AGE_TOO_SHORT)
    @Max(value = 120, message = Constants.AGE_TOO_HIGH)
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

    @ManyToMany
    @JoinTable(name = "friends",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id", referencedColumnName = "id")
    )
    private List<User> friends;

    @OneToMany(targetEntity = Album.class, mappedBy = "user")
    private List<Album> albums;

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);
    }
}
