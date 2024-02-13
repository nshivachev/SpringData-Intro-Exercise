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

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    @OneToMany(targetEntity = User.class, mappedBy = "bornTown")
    @Column(name = "born_users")
    private List<User> bornUsers;

    @OneToMany(targetEntity = User.class, mappedBy = "currentTown")
    @Column(name = "current_users")
    private List<User> currentUsers;
}
