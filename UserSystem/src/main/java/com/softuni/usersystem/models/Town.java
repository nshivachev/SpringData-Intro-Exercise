package com.softuni.usersystem.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "towns")
public class Town extends BaseEntity {
    @Basic
    private String name;

    @Basic
    private String country;

    @OneToMany(targetEntity = User.class, mappedBy = "bornTown",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(name = "born_users")
    private List<User> bornUsers;

    @OneToMany(targetEntity = User.class, mappedBy = "currentTown",
            fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Column(name = "current_users")
    private List<User> currentUsers;
}
